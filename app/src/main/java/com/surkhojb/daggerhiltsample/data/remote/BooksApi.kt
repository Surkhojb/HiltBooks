package com.surkhojb.daggerhiltsample.data.remote

import com.surkhojb.daggerhiltsample.data.remote.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    @GET("volumes")
    suspend fun getBooksBySearch(
        @Query("q") searchQuery: String,
        @Query("maxResult") maxResult: Int? = 0,
        @Query("orderBy") orderBy: String): BookResponse
}