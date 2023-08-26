package com.saadahmedsoft.sparkconvo.helper

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

infix fun View.onClicked(onClick: (View) -> Unit) {
    setOnClickListener { onClick(it) }
}

infix fun View.onLongPressed(onPress: (View) -> Boolean){
    setOnLongClickListener {onPress(it)}
}

fun View.setBackground(@DrawableRes resId: Int) = setBackgroundResource(resId)

fun Activity.findColor(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.enable() {
    this.isEnabled = true
    this.alpha = 1f
}

fun View.disable() {
    this.isEnabled = false
    this.alpha = 0.4.toFloat()
}

fun Context.setImageTint(iv: ImageView, color: Int) {
    iv.setColorFilter(ContextCompat.getColor(this, color), android.graphics.PorterDuff.Mode.SRC_IN)
}

fun View.changeBackground(@DrawableRes drawable: Int) {
    this.setBackgroundResource(drawable)
}