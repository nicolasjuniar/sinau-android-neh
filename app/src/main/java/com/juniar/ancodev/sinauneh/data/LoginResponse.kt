package com.juniar.ancodev.sinauneh.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") private val status: String,
    @SerializedName("message") private val message: String,
    @SerializedName("data") private val data: LoginModel
)

data class LoginModel(
    @SerializedName("token") private val token: String
)