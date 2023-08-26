package com.saadahmedsoft.sparkconvo.helper

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner

fun Spinner.addAdapter(context: Context, list: List<String>) {
    val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, list)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    this.adapter = adapter
}