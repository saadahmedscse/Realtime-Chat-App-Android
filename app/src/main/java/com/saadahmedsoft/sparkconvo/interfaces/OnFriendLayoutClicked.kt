package com.saadahmedsoft.sparkconvo.interfaces

import com.saadahmedsoft.sparkconvo.service.dto.user.ProfileResponse

interface OnFriendLayoutClicked {
    fun onFriendClicked(item: ProfileResponse)
}