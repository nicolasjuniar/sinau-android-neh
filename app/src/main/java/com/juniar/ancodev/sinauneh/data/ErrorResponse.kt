package com.juniar.ancodev.sinauneh.data

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status") private val status: String,
    @SerializedName("error") private val error: ErrorModel
)

data class ErrorModel(
    @SerializedName("code") private val code: Int,
    @SerializedName("error") private val error: ArrayList<String>
)