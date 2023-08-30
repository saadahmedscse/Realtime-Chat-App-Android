package com.saadahmedsoft.sparkconvo.service.dto.conversation

import com.google.gson.annotations.SerializedName
import com.saadahmedsoft.sparkconvo.service.dto.user.ProfileResponse


data class HomeConversationResponse (

  @SerializedName("id"          ) var id          : Int?             = null,
  @SerializedName("lastMessage" ) var lastMessage : String?          = null,
  @SerializedName("friend"      ) var friend      : ProfileResponse? = null,
  @SerializedName("createdAt"   ) var createdAt   : String?          = null,
  @SerializedName("updatedAt"   ) var updatedAt   : String?          = null

)