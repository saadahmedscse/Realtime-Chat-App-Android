package com.saadahmedsoft.sparkconvo.service.dto.auth

import android.net.Uri

data class CreateAccountRequest(
    val photo: Uri,
    val name: String,
    val email: String,
    val gender: String
)
