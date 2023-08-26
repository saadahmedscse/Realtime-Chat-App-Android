package com.saadahmedsoft.sparkconvo.helper

import android.content.Context
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun TextView.setTextColor(context: Context, @ColorRes color: Int) {
    this.setTextColor(ContextCompat.getColor(context, color))
}

fun TextView.getString() = this.text.toString()