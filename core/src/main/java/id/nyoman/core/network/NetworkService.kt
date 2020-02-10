package id.nyoman.core.network

import id.nyoman.core.BuildConfig
import id.nyoman.core.data.LoginResponse
import id.nyoman.core.data.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkService {

    @POST("auth/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<LoginResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<NowPlayingResponse>
}