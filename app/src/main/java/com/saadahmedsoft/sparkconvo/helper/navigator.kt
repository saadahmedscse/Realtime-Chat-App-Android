package com.saadahmedsoft.sparkconvo.helper

import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.saadahmedsoft.sparkconvo.R

fun View.navigate(id: Int) {
    val navBuilder = NavOptions.Builder()
    navBuilder
        .setEnterAnim(R.anim.fade_enter)
        .setExitAnim(R.anim.fade_exit)
        .setPopEnterAnim(R.anim.fade_enter)
        .setPopExitAnim(R.anim.fade_exit)

    Navigation.findNavController(this).navigate(id, null, navBuilder.build())
}