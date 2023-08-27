package com.saadahmedsoft.sparkconvo.helper

import android.content.Context
import com.google.gson.JsonObject
import com.saadahmedsoft.sparkconvo.util.SessionManager

fun Context.getToken(): String? = SessionManager.getInstance(this).token
fun Context.getBearerToken(): String? = SessionManager.getInstance(this).bearerToken
fun Context.getPhone(): String? = SessionManager.getInstance(this).number