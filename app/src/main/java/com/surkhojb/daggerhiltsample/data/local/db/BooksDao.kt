package com.surkhojb.daggerhiltsample.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surkhojb.daggerhiltsample.data.local.model.Book

@Dao
public interface BooksDao {

    @Query("SELECT * FROM books")
    fun getBooks(): List<Book>

    @Query("SELECT COUNT(*) FROM books")
    fun hasBooksCached(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBooks(books: List<Book>)
}