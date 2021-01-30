package com.surkhojb.daggerhiltsample.data

import com.surkhojb.daggerhiltsample.common.Resource
import com.surkhojb.daggerhiltsample.data.local.BooksLocalDataSource
import com.surkhojb.daggerhiltsample.data.mapper.BooksMapper
import com.surkhojb.daggerhiltsample.data.remote.BooksRemoteDataSource
import com.surkhojb.daggerhiltsample.data.remote.model.BookResponse
import com.surkhojb.daggerhiltsample.model.Books
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class BooksRepository @Inject constructor(
    val remoteDataSource: BooksRemoteDataSource,
    val localDataSource: BooksLocalDataSource,
    val settingsRepository: SettingsRepository) {

    @Inject
    lateinit var booksMapper: BooksMapper

    suspend fun getBooksBySearch(query: String): Resource<Books> {
        return try {
            //if network is not avaliable and we have saved books we return cached books
            if(!isNetworkAvaliable() && areBooksCached()){
                return getCachedBooks()
            }

            val maxResult = getMaxResults()
            val orderBy = getOrderBy()
            val offlineSupport = getOfflineSupport()

            val bookResponse = remoteDataSource.getBooksBySearch(query,maxResult,orderBy)

            //If offline support is enabled we save books in the database
            if(offlineSupport){
                cacheAndGetBooks(bookResponse)
            }else {
                val books = booksMapper.remoteBooksToModel(bookResponse)
                Resource.success(books)
            }

        }catch (ex: Exception){
            Resource.error(ex,null)
        }
    }

    private suspend fun cacheAndGetBooks(bookResponse: BookResponse): Resource<Books> {
        localDataSource.cacheBooks(booksMapper.remoteBooksToDbBooks(bookResponse))
        val books = booksMapper.mapToModel(localDataSource.getAllBooks())
        return Resource.success(books)
    }

    private suspend fun getCachedBooks(): Resource<Books> {
        val cachedBooks = booksMapper.mapToModel(localDataSource.getAllBooks())
        return Resource.success(cachedBooks)
    }

    private suspend fun getMaxResults() = settingsRepository.getMaxResult()

    private suspend fun getOrderBy() = settingsRepository.getOrderBy()

    private suspend fun getOfflineSupport() = settingsRepository.getOfflineSupport()

    private fun isNetworkAvaliable() = settingsRepository.isNetworkAvaliable() ?: false

    private suspend fun areBooksCached() = localDataSource.hasBooksCached()
}