package com.surkhojb.daggerhiltsample.features.list_books

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.surkhojb.daggerhiltsample.common.BaseViewModel
import com.surkhojb.daggerhiltsample.common.Resource
import com.surkhojb.daggerhiltsample.data.BooksRepository
import com.surkhojb.daggerhiltsample.model.Books
import kotlinx.coroutines.launch

class ListViewModel @ViewModelInject constructor(val booksRepository: BooksRepository) : BaseViewModel() {

    private val _books: MutableLiveData<Resource<Books>> = MutableLiveData()
    val books: LiveData<Resource<Books>>
        get() = _books

    fun getBooksByQuery(query: String?){
        if(query.isNullOrEmpty()) {
            _books.value = Resource.error("Query parameter can't be empty")
            return
        }

        launch {
            _books.value = Resource.loading(null)
            _books.value = booksRepository.getBooksBySearch(query)
        }
    }

}