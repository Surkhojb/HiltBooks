package com.surkhojb.daggerhiltsample.features.list_books

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surkhojb.daggerhiltsample.common.BaseViewModel
import com.surkhojb.daggerhiltsample.data.BooksRepository
import com.surkhojb.daggerhiltsample.model.Books
import kotlinx.coroutines.launch

class ListViewModel @ViewModelInject constructor(val booksRepository: BooksRepository) : BaseViewModel() {

    private val _books: MutableLiveData<Books> = MutableLiveData()
    val books: LiveData<Books>
        get() = _books

    fun getBooksByQuery(query: String = "Stephen King"){
        launch {
            _books.value = booksRepository.getBooksBySearch(query)
        }
    }

}