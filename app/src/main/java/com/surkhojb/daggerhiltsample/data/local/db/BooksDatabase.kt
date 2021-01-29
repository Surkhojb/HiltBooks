package com.surkhojb.daggerhiltsample.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.surkhojb.daggerhiltsample.data.local.db.BooksDao
import com.surkhojb.daggerhiltsample.data.local.model.Book

@Database(entities = arrayOf(Book::class), version = 1)
@TypeConverters(ListStringTypeConverter::class)
abstract class BooksDatabase : RoomDatabase(){

    abstract fun booksDao(): BooksDao
}