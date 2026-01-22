package com.valtx.bank.data.di

import com.valtx.bank.BuildConfig
import com.valtx.bank.data.api.RestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_READ = 40L
    private const val TIMEOUT_WRITE = 40L
    private const val TIMEOUT_CONNECT = 30L
    private const val LANGUAGE = "language"
    private const val LANGUAGE_ES = "es-PE"

    @Provides
    @Singleton
    fun providesOkHttpClient(
        logging: HttpLoggingInterceptor,
        queryInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(queryInterceptor)
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun providesQueryInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter(LANGUAGE, LANGUAGE_ES)
            .build()

        val newRequest = request.newBuilder().url(newUrl).build()
        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
    fun providesJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    @Singleton
    @Provides
    fun providesKotlinSerializationConverter(json: Json): Converter.Factory =
        json.asConverterFactory(
            "application/json; charset=utf-8".toMediaType()
        )

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): RestApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build().create(RestApi::class.java)
}