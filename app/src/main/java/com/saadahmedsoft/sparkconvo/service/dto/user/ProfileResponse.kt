package com.saadahmedsoft.sparkconvo.service.dto.user

import com.google.gson.annotations.SerializedName


data class ProfileResponse (

  @SerializedName("id"        ) var id        : Long?    = null,
  @SerializedName("name"      ) var name      : String? = null,
  @SerializedName("email"     ) var email     : String? = null,
  @SerializedName("photo"     ) var photo     : String? = null,
  @SerializedName("gender"    ) var gender    : String? = null,
  @SerializedName("createdAt" ) var createdAt : String? = null,
  @SerializedName("updatedAt" ) var updatedAt : String? = null

)