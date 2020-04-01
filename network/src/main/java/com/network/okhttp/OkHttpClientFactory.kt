package com.network.okhttp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    private const val CONNECTION_TIME_OUT: Long = 1
    private lateinit var okHttpInstance: OkHttpClient.Builder

    fun getInstance(header: HashMap<String, String>?, isDebug: Boolean): OkHttpClient {

        okHttpInstance = OkHttpClient().newBuilder()
        okHttpInstance.connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MINUTES)

        if (header != null && header.size > 0){

           /* https://gist.github.com/lfmingo/768a29eafc4a9d25d75ef31e2be506f7
            val original: Request = chain.request()
            val builder = original.newBuilder().method(original.method(), original.body())
            for ((key, value) in header.entries) {
                builder.header(key, value)
            }
            return chain.proceed(builder.build())
            */

        }

        if (isDebug) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpInstance.addInterceptor(loggingInterceptor)
        }

        return okHttpInstance.build()
    }

    fun getInstance(): OkHttpClient {
        return getInstance(null,false)
    }

    fun getInstance(isDebug: Boolean): OkHttpClient {
        return getInstance(null,isDebug)
    }

    fun getInstance(header: HashMap<String, String>): OkHttpClient {
        return getInstance(header,false)
    }
}