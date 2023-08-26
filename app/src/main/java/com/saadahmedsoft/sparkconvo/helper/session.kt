package com.saadahmedsoft.sparkconvo.helper

import android.content.Context
import com.google.gson.JsonObject
import com.saadahmedsoft.sparkconvo.util.SessionManager

fun Context.getToken(): String = SessionManager.getInstance(this).token!!
fun Context.getPhone(): String = SessionManager.getInstance(this).number!!
fun Context.getPhoneAsBody(): JsonObject {
    val body = JsonObject()
    body.addProperty("phone", this.getPhone())
    return body
}