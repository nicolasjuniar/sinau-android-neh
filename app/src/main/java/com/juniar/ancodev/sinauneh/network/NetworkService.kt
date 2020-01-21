package com.juniar.ancodev.sinauneh.network

import com.juniar.ancodev.sinauneh.data.LoginResponse
import com.juniar.ancodev.sinauneh.data.PostModel
import com.juniar.ancodev.sinauneh.data.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("posts")
    suspend fun getAllPosts(): Response<List<PostModel>>

    @POST("auth/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<LoginResponse>

    @GET("users/{id}")
    suspend fun getUsers(@Path("id") id: Int): Response<UserModel>
}