package com.network.okhttp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    private const val CONNECTION_TIME_OUT: Long = 1
    private lateinit var okHttpInstance: OkHttpClient.Builder

    fun getInstance(isDebug: Boolean): OkHttpClient {

        okHttpInstance = OkHttpClient().newBuilder()
        okHttpInstance.connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MINUTES)
        if (isDebug) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpInstance.addInterceptor(loggingInterceptor)
        }

        return okHttpInstance.build()
    }

    fun getInstance(): OkHttpClient {
        return getInstance(false)
    }
}