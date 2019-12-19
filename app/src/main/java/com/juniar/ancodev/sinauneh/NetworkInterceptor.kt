package com.juniar.ancodev.sinauneh

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
//        val useAuth = request.url().pathSegments().last() !in arrayOf("signin", "refreshToken")
//
//        if (useAuth) {
//            request = request.newBuilder()
//                .addHeader("Authorization", "Bearer ${store.authToken}")
//                .addHeader("UserSessionToken", store.sessionToken)
//                .build()
//        }

        //        checkResponseCode(response)
        val response = try {
            chain.proceed(request)
        } catch (error: Exception) {
            val message = when (error) {
                is UnknownHostException, is ConnectException -> "Cannot connect to server. Please check your internet connection."
                is SocketTimeoutException -> "Connection timeout. Please try again later."
                else -> "Something went wrong"
            }
            Log.e("cocote salah", error.message)
            throw IOException(message)
        }
        if (response.code in 400..500) {
            Log.e("cocote salah", "cocote")
        }

        return response
    }
}