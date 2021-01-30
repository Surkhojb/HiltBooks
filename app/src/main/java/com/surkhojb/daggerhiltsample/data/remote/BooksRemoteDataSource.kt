package com.surkhojb.daggerhiltsample.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksRemoteDataSource @Inject constructor(val booksApi: BooksApi) {

    suspend fun getBooksBySearch(query: String, maxResult: Int? = 0,orderBy: String) = withContext(Dispatchers.IO){
        booksApi.getBooksBySearch(query,maxResult,orderBy)
    }
}