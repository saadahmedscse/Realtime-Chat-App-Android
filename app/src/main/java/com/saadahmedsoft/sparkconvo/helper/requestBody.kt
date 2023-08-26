package com.saadahmedsoft.sparkconvo.helper

import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun EditText.createBody(): RequestBody {
    return this.text.toString().ifBlank { "" }.toRequestBody("text/plain".toMediaTypeOrNull())
}

fun TextView.createBody(): RequestBody {
    return this.text.toString().ifBlank { "" }.toRequestBody("text/plain".toMediaTypeOrNull())
}

fun String.createBody(): RequestBody {
    return this.ifBlank { "" }.toRequestBody("text/plain".toMediaTypeOrNull())
}

fun TextInputLayout.createBody(): RequestBody {
    return this.getString().ifBlank { "" }.toRequestBody("text/plain".toMediaTypeOrNull())
}