package com.saadahmedsoft.sparkconvo.helper

import android.content.Context
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun ImageView.setTint(context: Context, @ColorRes color: Int) {
    this.setColorFilter(ContextCompat.getColor(context, color), android.graphics.PorterDuff.Mode.SRC_IN)
}