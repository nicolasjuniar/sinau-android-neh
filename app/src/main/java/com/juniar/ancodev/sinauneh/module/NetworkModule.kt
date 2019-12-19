package com.juniar.ancodev.sinauneh.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.juniar.ancodev.sinauneh.BuildConfig
import com.juniar.ancodev.sinauneh.BuildConfig.BASE_URL
import com.juniar.ancodev.sinauneh.NetworkInterceptor
import com.juniar.ancodev.sinauneh.network.NetworkRepository
import com.juniar.ancodev.sinauneh.network.NetworkService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    fun getModules(): Module {
        return module {
            single { providesGson() }
            single { providesLoggingInterceptor() }
            single { providesOkHttpClient(get()) }
            single { NetworkInterceptor() }
            single { providesRetrofit(get(), get()) }
            single { providesNetworkService(get()) }
            single { providesNetworkRepository(get()) }
        }
    }

    private val REQUEST_TIMEOUT = 10

    fun providesNetworkService(retrofit: Retrofit): NetworkService = retrofit.create(NetworkService::class.java)

    fun providesGson() = GsonBuilder()
        .setLenient()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .create()

    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    fun providesNetworkRepository(networkService: NetworkService) =
        NetworkRepository(networkService)
}