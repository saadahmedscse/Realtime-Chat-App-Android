package com.saadahmedsoft.sparkconvo.service.dto.conversation

import com.google.gson.annotations.SerializedName
import java.util.LinkedList


data class ConversationResponse (

  @SerializedName("id"        ) var id        : Long?             = null,
  @SerializedName("p1Email"   ) var p1Email   : String?          = null,
  @SerializedName("p2Email"   ) var p2Email   : String?          = null,
  @SerializedName("p1Photo"   ) var p1Photo   : String?          = null,
  @SerializedName("p2Photo"   ) var p2Photo   : String?          = null,
  @SerializedName("createdAt" ) var createdAt : String?          = null,
  @SerializedName("updatedAt" ) var updatedAt : String?          = null,
  @SerializedName("chats"     ) var chat     : LinkedList<Chats> = LinkedList()

)