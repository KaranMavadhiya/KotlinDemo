package com.network.retrofit

import com.network.okhttp.OkHttpClientFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientFactory {

    private fun getService(  baseUrl: String, header: HashMap<String, String>?, isDebug: Boolean): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClientFactory.getInstance(header, isDebug))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getInstance( baseUrl: String, header: HashMap<String, String>, isDebug: Boolean, interfaceClass: Class<T>): T {
        return getService(baseUrl, header, isDebug).create(interfaceClass)
    }

    /*
     * default value of header: HashMap<String, String> is null
     * default value of isDebug: Boolean is false
     */
    fun <T> getInstance(baseUrl: String, interfaceClass: Class<T>): T {
        return getService(baseUrl, null, false).create(interfaceClass)
    }

    /*
     * default value of isDebug: Boolean is false
     */
    fun <T> getInstance(  baseUrl: String, header: HashMap<String, String>, interfaceClass: Class<T>): T {
        return getService(baseUrl, header, false).create(interfaceClass)
    }
}