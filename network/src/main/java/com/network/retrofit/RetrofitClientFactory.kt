package com.network.retrofit

import com.network.BuildConfig
import com.network.okhttp.OkHttpClientFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientFactory {

    private fun getService(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(OkHttpClientFactory.getInstance(BuildConfig.DEBUG))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getInstance(interfaceClass: Class<T>): T {
        return getService(BuildConfig.API_URL).create(interfaceClass)
    }

    fun <T> getInstance(url: String, interfaceClass: Class<T>): T {
        return getService(url).create(interfaceClass)
    }
}