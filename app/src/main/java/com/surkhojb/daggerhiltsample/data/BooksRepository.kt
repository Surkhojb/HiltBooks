package com.surkhojb.daggerhiltsample.data

import com.surkhojb.daggerhiltsample.common.Resource
import com.surkhojb.daggerhiltsample.data.local.BooksLocalDataSource
import com.surkhojb.daggerhiltsample.data.mapper.BooksMapper
import com.surkhojb.daggerhiltsample.data.remote.BooksRemoteDataSource
import com.surkhojb.daggerhiltsample.model.Books
import java.lang.Exception
import javax.inject.Inject

class BooksRepository @Inject constructor(
    val remoteDataSource: BooksRemoteDataSource,
    val localDataSource: BooksLocalDataSource) {

    @Inject
    lateinit var booksMapper: BooksMapper

    suspend fun getBooksBySearch(query: String): Resource<Books> {
       return try{

            val bookResponse = remoteDataSource.getBooksBySearch(query)

            localDataSource.cacheBooks(booksMapper.remoteBooksToDbBooks(bookResponse))
            val books = booksMapper.mapToModel(localDataSource.getAllBooks())

            Resource.success(books)
        }catch (ex: Exception){
            Resource.error(ex,null)
        }
    }
}