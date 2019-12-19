package com.juniar.ancodev.sinauneh

import retrofit2.Response
import retrofit2.Retrofit

class RetrofitDelegate {

//    var retrofit: Retrofit? = null
//
//    private val authService: AuthService?
//        get() = retrofit?.create(AuthService::class.java)
//
//    fun getError(response: Response): ErrorResponse? {
//        if (response.code() in 200..299) {
//            return null
//        }
//
//        val converter = retrofit?.responseBodyConverter<ErrorResponse>(
//            ErrorResponse::class.java,
//            ErrorResponse::class.java.annotations
//        )
//        return try {
//            converter?.convert(response.peekBody(Long.MAX_VALUE))
//        } catch (error: Exception) {
//            ErrorResponse(null, 0, "Response converter error", "Something went wrong", null)
//        }
//    }
//
//    fun refreshToken(refreshToken: String): Token? {
//        return try {
//            val response = authService?.refreshToken(RefreshTokenParam(refreshToken))
//                ?.execute()
//                ?.body()
//            response?.token
//        } catch (error: Exception) {
//            Timber.e("Error refreshing token: ${error.message}")
//            null
//        }
//    }
}