package com.saadahmedsoft.sparkconvo.helper

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

fun linearLayoutManager(context: Context) = LinearLayoutManager(context)
fun staggeredGridLayoutManager(itemCount: Int) = StaggeredGridLayoutManager(itemCount, LinearLayoutManager.VERTICAL)
fun RecyclerView.setLinearLayoutManager(context: Context) {
    this.layoutManager = linearLayoutManager(context)
}

fun RecyclerView.setReverseLayoutManager(context: Context) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
}

fun RecyclerView.setStaggeredGridLayoutManager(gridCount: Int) {
    this.layoutManager = staggeredGridLayoutManager(gridCount)
}

fun RecyclerView.setHorizontalLayoutManager(context: Context) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

fun Context.setLinearLayoutManager(vararg recyclerViews: RecyclerView) {
    for (rv in recyclerViews) rv.setLinearLayoutManager(this)
}

fun Context.setHorizontalLayoutManager(vararg recyclerViews: RecyclerView) {
    for (rv in recyclerViews) rv.setHorizontalLayoutManager(this)
}