package id.nyoman.core.network

import id.nyoman.core.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}