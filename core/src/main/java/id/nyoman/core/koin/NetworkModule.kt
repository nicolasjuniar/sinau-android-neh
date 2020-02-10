package id.nyoman.core.koin

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import org.koin.android.ext.koin.androidContext
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import id.nyoman.core.BuildConfig.BASE_URL
import id.nyoman.core.network.HeaderInterceptor
import id.nyoman.core.network.NetworkInterceptor
import id.nyoman.core.network.NetworkRepository
import id.nyoman.core.network.NetworkService
import id.nyoman.core.utils.DiffCallback
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
            single { providesOkHttpClient(get(), androidContext()) }
            single { NetworkInterceptor() }
            single { providesRetrofit(get(), get()) }
            single { providesNetworkService(get()) }
            single { providesNetworkRepository(get()) }
            single { DiffCallback() }
        }
    }

    private val REQUEST_TIMEOUT = 10

    fun providesNetworkService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)

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

    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, context: Context) =
        OkHttpClient.Builder()
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(HeaderInterceptor() )
            .addInterceptor(ChuckerInterceptor(context))
            .build()

    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    fun providesNetworkRepository(networkService: NetworkService) =
        NetworkRepository(networkService)
}