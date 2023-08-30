package com.saadahmedsoft.sparkconvo.interfaces

import com.saadahmedsoft.sparkconvo.service.dto.conversation.HomeConversationResponse

interface OnConversationClicked {
    fun onConversationClicked(item: HomeConversationResponse)
}