package com.surkhojb.daggerhiltsample.features.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.surkhojb.daggerhiltsample.R
import com.surkhojb.daggerhiltsample.common.AppConstants
import com.surkhojb.daggerhiltsample.common.AppConstants.BOOK_INTENT_KEY
import com.surkhojb.daggerhiltsample.common.loadIntoCustomTab
import com.surkhojb.daggerhiltsample.common.loadUrl
import com.surkhojb.daggerhiltsample.databinding.ActivityDetailBookBinding
import com.surkhojb.daggerhiltsample.model.BookInfo

class DetailBookActivity : AppCompatActivity() {

    private var binding: ActivityDetailBookBinding? = null
    private var bookItem: BookInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        bookItem = intent?.getSerializableExtra(BOOK_INTENT_KEY) as? BookInfo
        bookItem?.let {
            buildBookInfo(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun buildBookInfo(bookInfo: BookInfo){
        supportActionBar?.title = bookInfo.title
        binding?.cover?.loadUrl(bookInfo.thumbnail)
        binding?.author?.text = getString(R.string.detail_author,bookInfo.authors.firstOrNull() ?: "Unknown author")
        binding?.pages?.text = getString(R.string.detail_pages,bookInfo.pageCount.toString())
        binding?.publisher?.text = getString(R.string.detail_publisher,bookInfo.publisher)
        binding?.publishDate?.text =getString(R.string.detail_published,bookInfo.publishedDate)
        binding?.overview?.text = bookInfo.description
        binding?.moreInfo?.text = getString(R.string.detail_go_to_store)
        binding?.preview?.text = getString(R.string.detail_go_to_preview)

        binding?.moreInfo?.setOnClickListener(this::handleLinksClick)
        binding?.preview?.setOnClickListener(this::handleLinksClick)

    }

    private fun handleLinksClick(view: View){
        when(view.id){
            R.id.more_info -> { goToMoreInfo(bookItem?.infoLink)}
            R.id.preview -> { goToPreview(bookItem?.previewLink)}
        }
    }

    private fun goToMoreInfo(link: String?){
        link?.loadIntoCustomTab(this)
    }

    private fun goToPreview(link: String?){
        val previewLink = getString(R.string.preview_link,bookItem?.volumeId)
        previewLink.loadIntoCustomTab(this)
    }
}