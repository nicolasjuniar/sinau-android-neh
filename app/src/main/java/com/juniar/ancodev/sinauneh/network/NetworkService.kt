package com.juniar.ancodev.sinauneh.network

import com.juniar.ancodev.sinauneh.data.PostModel
import retrofit2.http.GET

interface NetworkService {
    @GET("posts")
    suspend fun getAllPosts(): ArrayList<PostModel>
}