package com.saadahmedsoft.sparkconvo.api

import com.saadahmedsoft.sparkconvo.service.dto.common.CommonResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface ApiService {

    @Headers("Accept: application/json")
    @Multipart
    @JvmSuppressWildcards
    @POST("create-account")
    fun createAccount(
        @PartMap userRequest: Map<String, RequestBody>,
        @Part photo: MultipartBody.Part?
    ): Call<CommonResponse>
}