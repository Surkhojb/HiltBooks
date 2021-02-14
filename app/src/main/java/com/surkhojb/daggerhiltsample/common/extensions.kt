package com.surkhojb.daggerhiltsample.common

import android.content.Context
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.bumptech.glide.Glide
import com.surkhojb.daggerhiltsample.R

fun ImageView.loadUrl(url: String){
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.cover_not_avaliable)
        .into(this)
}

fun NavController.navigateWithDefaultAnimation(@IdRes viewId: Int) {
    return this.navigate(
        viewId, null, NavOptions.Builder()
            .setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
            .build()
    )
}

//When String is a url we could use this extension to load on CustomTab if we want it.
fun String.loadIntoCustomTab(context: Context){
    val builder = CustomTabsIntent.Builder()
        .setToolbarColor(ContextCompat.getColor(context,R.color.purple_500))
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(this))
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

@Suppress("DEPRECATION")
fun View.openKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInputFromInputMethod(windowToken, 0)
}