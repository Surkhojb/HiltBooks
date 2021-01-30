package com.surkhojb.daggerhiltsample.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.surkhojb.daggerhiltsample.R

fun ImageView.loadUrl(url: String){
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.cover_not_avaliable)
        .into(this)
}