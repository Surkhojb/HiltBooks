package com.surkhojb.daggerhiltsample.features.list_books.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surkhojb.daggerhiltsample.R
import com.surkhojb.daggerhiltsample.common.loadUrl
import com.surkhojb.daggerhiltsample.features.list_books.adapter.BooksAdapter.BookInfoViewHolder
import com.surkhojb.daggerhiltsample.model.BookInfo

class BooksAdapter(val navigateToDetail: (BookInfo) -> Unit ): ListAdapter<BookInfo, BookInfoViewHolder>(BookInfoDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_book_item,parent,false)
        return BookInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val cover: ImageView = itemView.findViewById(R.id.cover_book)
        private val title: TextView = itemView.findViewById(R.id.title_book)
        private val author: TextView = itemView.findViewById(R.id.author_book)

        fun bind(item: BookInfo){
            cover.loadUrl(item.thumbnail)
            title.text = item.title
            author.text = item.authors.firstOrNull() ?: "-"
            itemView.setOnClickListener { navigateToDetail.invoke(item) }
        }
    }

    class BookInfoDiffUtilCallback: DiffUtil.ItemCallback<BookInfo>(){
        override fun areItemsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean {
          return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean {
            return oldItem == newItem
        }

    }
}

