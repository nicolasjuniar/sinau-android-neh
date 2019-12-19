package com.juniar.ancodev.sinauneh.data

import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)