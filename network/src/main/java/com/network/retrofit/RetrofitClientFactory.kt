package com.network.retrofit

import com.network.BuildConfig
import com.network.okhttp.OkHttpClientFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientFactory {

    private fun getService(header: HashMap<String, String>?, url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(OkHttpClientFactory.getInstance(header, BuildConfig.DEBUG))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getInstance(interfaceClass: Class<T>): T {
        return getService(null, BuildConfig.API_URL).create(interfaceClass)
    }

    fun <T> getInstance(header: HashMap<String, String>, interfaceClass: Class<T>): T {
        return getService(header, BuildConfig.API_URL).create(interfaceClass)
    }

    fun <T> getInstance(url: String, interfaceClass: Class<T>): T {
        return getService(null, url).create(interfaceClass)
    }

    fun <T> getInstance(url: String, header: HashMap<String, String>, interfaceClass: Class<T>): T {
        return getService(header, url).create(interfaceClass)
    }
}