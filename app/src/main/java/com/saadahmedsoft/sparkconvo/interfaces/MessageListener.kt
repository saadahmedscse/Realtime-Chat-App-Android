package com.saadahmedsoft.sparkconvo.interfaces

interface MessageListener {
    fun onSocketMessageReceived(message: String?)
}