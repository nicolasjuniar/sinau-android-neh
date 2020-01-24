package id.nyoman.core.network

import id.nyoman.core.data.LoginResponse
import id.nyoman.core.data.PostModel
import id.nyoman.core.data.UserModel
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