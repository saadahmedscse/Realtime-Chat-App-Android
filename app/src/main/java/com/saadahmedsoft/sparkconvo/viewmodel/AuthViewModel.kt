package com.saadahmedsoft.sparkconvo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saadahmedsoft.sparkconvo.service.dto.auth.CreateAccountRequest

class AuthViewModel : ViewModel() {

    private val _createAccountRequest = MutableLiveData<CreateAccountRequest>()
    val createAccountRequest: LiveData<CreateAccountRequest>
        get() = _createAccountRequest

    fun setCreateAccountRequest(request: CreateAccountRequest) {
        _createAccountRequest.postValue(request)
    }
}