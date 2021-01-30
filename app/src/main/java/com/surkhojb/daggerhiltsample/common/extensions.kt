package com.surkhojb.daggerhiltsample.common

import android.app.Activity
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.surkhojb.daggerhiltsample.R

fun ImageView.loadUrl(url: String){
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.cover_not_avaliable)
        .into(this)
}

fun NavController.navigateWithDefaultAnimation(@IdRes viewId: Int) {
    return this.navigate(viewId,null, NavOptions.Builder()
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
        .build())
}