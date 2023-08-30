package com.saadahmedsoft.sparkconvo.view.dashboard.tabs.chat

import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.saadahmedsoft.sparkconvo.base.BaseFragment
import com.saadahmedsoft.sparkconvo.databinding.FragmentChatBinding
import com.saadahmedsoft.sparkconvo.helper.getString
import com.saadahmedsoft.sparkconvo.helper.onClicked
import com.saadahmedsoft.sparkconvo.helper.setReverseLayoutManager
import com.saadahmedsoft.sparkconvo.interfaces.MessageListener
import com.saadahmedsoft.sparkconvo.service.dto.conversation.Chats
import com.saadahmedsoft.sparkconvo.service.dto.user.ProfileResponse
import com.saadahmedsoft.sparkconvo.view.dashboard.tabs.chat.adapter.ChatAdapter
import com.saadahmedsoft.sparkconvo.websocket.AppWebSocketClient
import com.squareup.picasso.Picasso
import org.java_websocket.client.WebSocketClient
import java.net.URI
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatFragment : BaseFragment<FragmentChatBinding>(FragmentChatBinding::inflate), MessageListener {

    private lateinit var adapter: ChatAdapter
    private var senderId: Long? = null
    private var conversationId: Long? = null
    private var lastId: Long? = null
    private lateinit var webSocketClient: WebSocketClient

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        val p1Email = tinyDb.getString("p1Email", null)
        val p2Email = tinyDb.getString("p2Email", null)
        val friendProfile = tinyDb.getObject<ProfileResponse>("friend_profile", ProfileResponse::class.java)

        binding.recyclerView.setReverseLayoutManager(requireContext())
        adapter = ChatAdapter(friendProfile.id!!)
        binding.recyclerView.adapter = adapter

        Picasso.get().load("http://192.168.0.114/${friendProfile.photo}").into(binding.profilePicture)
        binding.tvName.text = friendProfile.name

        val body = JsonObject()
        body.addProperty("p1Email", p1Email)
        body.addProperty("p2Email", p2Email)

        apiService.getProfile(session.bearerToken!!).getNoProgressResponse {
            senderId = it.id
        }
        apiService.createConversation(session.bearerToken!!, body).getResponse("Checking conversation info, please wait.") {
            apiService.getSingleConversationByEmails(session.bearerToken!!, body).getResponse("Getting messages, please be patience") {
                conversationId = it.id
                adapter.addItems(it.chat)
            }
        }

        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val creationDate = simpleDateFormat.format(Date())

        binding.btnSend.onClicked {
            if (binding.etMessage.getString().isBlank()) {
                "Message cannot be empty".shortSnackBar()
                return@onClicked
            } else {
                lastId = if (lastId == null) 1 else lastId?.plus(1)
                val chat = Chats(
                    id = lastId,
                    conversationId = conversationId,
                    receiverId = friendProfile.id,
                    senderId = senderId,
                    message = binding.etMessage.getString(),
                    createdAt = creationDate,
                    updatedAt = creationDate
                )

                webSocketClient.send(Gson().toJson(chat))
                adapter.addItem(chat)
                binding.etMessage.text = null
            }
        }

        binding.btnBack.onClicked { onBackPressed() }
    }

    override fun observeData() {}

    override fun onResume() {
        super.onResume()

        val uri = URI("http://192.168.0.114:8080/send-realtime-message")
        webSocketClient = AppWebSocketClient(this, uri)
        webSocketClient.connect()
    }

    override fun onSocketMessageReceived(message: String?) {
        requireActivity().runOnUiThread {
            val chat = Gson().fromJson(message, Chats::class.java)
            if (senderId != chat.senderId) {
                adapter.addItem(chat)
            }
        }
    }
}