package com.surkhojb.daggerhiltsample.data.local

import com.surkhojb.daggerhiltsample.data.local.db.BooksDao
import com.surkhojb.daggerhiltsample.data.local.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksLocalDataSource @Inject constructor(val booksDao: BooksDao) {

    suspend fun getAllBooks() = withContext(Dispatchers.IO){
        booksDao.getBooks()
    }

    suspend fun cacheBooks(books: List<Book>) = withContext(Dispatchers.IO){
        booksDao.saveBooks(books)
    }
}