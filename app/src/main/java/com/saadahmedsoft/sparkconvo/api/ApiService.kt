package com.saadahmedsoft.sparkconvo.api

import com.google.gson.JsonObject
import com.saadahmedsoft.sparkconvo.service.dto.auth.LoginResponse
import com.saadahmedsoft.sparkconvo.service.dto.common.CommonResponse
import com.saadahmedsoft.sparkconvo.service.dto.user.ProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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

    @POST("login")
    fun login(@Body body: JsonObject): Call<LoginResponse>

    @GET("user/profile")
    fun getProfile(@Header("Authorization") token: String): Call<ProfileResponse>

    @GET("user")
    fun getFriends(@Header("Authorization") token: String): Call<List<ProfileResponse>>
}