package com.juniar.ancodev.sinauneh.network

import com.juniar.ancodev.sinauneh.data.LoginResponse
import com.juniar.ancodev.sinauneh.data.PostModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkService {
    @GET("posts")
    suspend fun getAllPosts(): Response<ArrayList<PostModel>>

    @POST("auth/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<LoginResponse>
}