package com.surkhojb.daggerhiltsample.data.mapper

import com.surkhojb.daggerhiltsample.data.remote.model.Item
import com.surkhojb.daggerhiltsample.model.BookInfo
import com.surkhojb.daggerhiltsample.model.Books
import javax.inject.Inject
import com.surkhojb.daggerhiltsample.data.local.model.Book as BookDb
import com.surkhojb.daggerhiltsample.data.remote.model.BookResponse as BookRemote

class BooksMapper @Inject constructor() {

    fun mapToModel(booksDb: List<BookDb>): Books{
        val listBooks = Books()
        booksDb.forEach {
            listBooks.books.add(map(it))
        }

        return listBooks
    }

    private fun map(bookDb: BookDb): BookInfo {
        return BookInfo().apply {
            this.title = bookDb.title
            this.subtitle = bookDb.subtitle
            this.authors = bookDb.authors
            this.publisher = bookDb.publisher
            this.publishedDate = bookDb.publishedDate
            this.description = bookDb.description
            this.pageCount = bookDb.pageCount
            this.categories = bookDb.categories
            this.smallThumbnail = bookDb.smallThumbnail
            this.thumbnail = bookDb.thumbnail
            this.previewLink = bookDb.previewLink
            this.infoLink = bookDb.infoLink
            this.canonicalVolumeLink = bookDb.canonicalVolumeLink
        }
    }


    fun remoteBooksToDbBooks(bookResponse: BookRemote): List<BookDb>{
        val list = arrayListOf<BookDb>()

        bookResponse.items.forEach {
            list.add(map(it))
        }

        return list
    }

    private fun map(item: Item) = BookDb(
        id = item.id,
        title = item.volumeInfo.title ?: "",
        subtitle = item.volumeInfo.subtitle ?: "",
        authors = item.volumeInfo.authors ?: emptyList(),
        publisher = item.volumeInfo.publisher ?: "",
        publishedDate = item.volumeInfo.publishedDate ?: "",
        description = item.volumeInfo.description ?: "",
        pageCount = item.volumeInfo.pageCount ?: 0,
        printType =  item.volumeInfo.printType ?: "",
        categories = item.volumeInfo.categories ?: emptyList(),
        maturityRating = item.volumeInfo.maturityRating ?: "",
        allowAnonLogging = item.volumeInfo.allowAnonLogging ?: false,
        contentVersion = item.volumeInfo.contentVersion?: "",
        thumbnail = item.volumeInfo.imageLinks?.thumbnail ?: "",
        smallThumbnail = item.volumeInfo.imageLinks?.smallThumbnail ?: "",
        language = item.volumeInfo.language ?: "",
        previewLink = item.volumeInfo.previewLink ?: "",
        infoLink = item.volumeInfo.infoLink ?: "",
        canonicalVolumeLink = item.volumeInfo.canonicalVolumeLink ?: "")
}