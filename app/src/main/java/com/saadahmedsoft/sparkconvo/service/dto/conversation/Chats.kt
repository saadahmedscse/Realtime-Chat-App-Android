package com.saadahmedsoft.sparkconvo.service.dto.conversation

import com.google.gson.annotations.SerializedName


data class Chats (

  @SerializedName("id"         ) var id         : Long?    = null,
  @SerializedName("senderId"   ) var senderId   : Long?    = null,
  @SerializedName("receiverId" ) var receiverId : Long?    = null,
  @SerializedName("message"    ) var message    : String? = null,
  @SerializedName("createdAt"  ) var createdAt  : String? = null,
  @SerializedName("updatedAt"  ) var updatedAt  : String? = null

)