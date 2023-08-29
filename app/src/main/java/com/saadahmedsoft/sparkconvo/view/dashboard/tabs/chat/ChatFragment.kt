package com.saadahmedsoft.sparkconvo.view.dashboard.tabs.chat

import android.os.Bundle
import com.google.gson.JsonObject
import com.saadahmedsoft.sparkconvo.R
import com.saadahmedsoft.sparkconvo.base.BaseFragment
import com.saadahmedsoft.sparkconvo.databinding.FragmentChatBinding
import com.saadahmedsoft.sparkconvo.helper.onClicked
import com.saadahmedsoft.sparkconvo.helper.setLinearLayoutManager
import com.saadahmedsoft.sparkconvo.helper.setReverseLayoutManager
import com.saadahmedsoft.sparkconvo.service.dto.user.ProfileResponse
import com.saadahmedsoft.sparkconvo.view.dashboard.tabs.chat.adapter.ChatAdapter
import com.squareup.picasso.Picasso

class ChatFragment : BaseFragment<FragmentChatBinding>(FragmentChatBinding::inflate) {

    private lateinit var adapter: ChatAdapter

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

        apiService.createConversation(session.bearerToken!!, body).getResponse("Checking conversation info, please wait.") {}
        apiService.getSingleConversationByEmails(session.bearerToken!!, body).getResponse("Getting messages, please be patience") {
            adapter.addItems(it.chat)
        }

        binding.btnBack.onClicked { onBackPressed() }
    }

    override fun observeData() {}
}