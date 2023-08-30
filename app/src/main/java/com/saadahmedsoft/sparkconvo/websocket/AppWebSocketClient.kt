package com.saadahmedsoft.sparkconvo.websocket

import com.saadahmedsoft.sparkconvo.interfaces.MessageListener
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

class AppWebSocketClient(private val listener: MessageListener, uri: URI) : WebSocketClient(uri) {

    override fun onOpen(handshakedata: ServerHandshake?) {}

    override fun onMessage(message: String?) {
        listener.onSocketMessageReceived(message)
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {}

    override fun onError(ex: Exception?) {}
}