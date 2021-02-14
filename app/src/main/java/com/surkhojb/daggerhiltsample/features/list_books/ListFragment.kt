package com.surkhojb.daggerhiltsample.features.list_books

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.surkhojb.daggerhiltsample.common.AppConstants.BOOK_INTENT_KEY
import com.surkhojb.daggerhiltsample.common.Resource
import com.surkhojb.daggerhiltsample.common.Status
import com.surkhojb.daggerhiltsample.common.hideKeyboard
import com.surkhojb.daggerhiltsample.databinding.ListFragmentBinding
import com.surkhojb.daggerhiltsample.features.detail.DetailBookActivity
import com.surkhojb.daggerhiltsample.features.list_books.adapter.BooksAdapter
import com.surkhojb.daggerhiltsample.model.BookInfo
import com.surkhojb.daggerhiltsample.model.Books
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private val adapter: BooksAdapter by lazy { BooksAdapter(navigateToDetail = {
        val intent = Intent(context,DetailBookActivity::class.java).apply{
            putExtra(BOOK_INTENT_KEY,it)
        }

        startActivity(intent)
    }) }

    private var binding: ListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setUpListeners()
        viewModelObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun viewModelObservers(){
        viewModel.books.observe(viewLifecycleOwner, Observer {
            Timber.d("Result ${it}")
            handleUiState(it)
        })
    }

    private fun handleUiState( resource: Resource<Books>){
        when(resource.status){
            Status.LOADING -> { showLoading(true) }
            Status.SUCCESS -> {
                showLoading(false)
                adapter.submitList(resource.data?.books ?: emptyList())
            }
            Status.ERROR -> {
                showLoading(false)
                Toast.makeText(context,resource.message,Toast.LENGTH_SHORT).show()}
        }
    }

    private fun showLoading(show: Boolean){
        binding?.loading?.visibility = if(show) View.VISIBLE else View.GONE
        binding?.listRecyclerview?.visibility = if(show) View.GONE else View.VISIBLE
    }

    private fun setUpListeners(){
        binding?.searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getBooksByQuery(query)
                binding?.searchView?.hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun setUpView(){
        binding?.listRecyclerview?.adapter = adapter
    }

}