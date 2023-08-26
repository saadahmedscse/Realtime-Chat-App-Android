package com.saadahmedsoft.sparkconvo.helper

import android.os.Handler
import android.os.Looper

inline fun delay(time: Long, crossinline block: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        block()
    }, time)
}