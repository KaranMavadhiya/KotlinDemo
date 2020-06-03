package com.kotlindemo.registration.api

import com.kotlindemo.registration.utils.Constants
import com.network.retrofit.RetrofitClientFactory

object NetworkInstance {
    fun getNetworkInterceptor(): NetworkInterceptor {
        return RetrofitClientFactory.getInstance(Constants.BASE_URL, NetworkInterceptor::class.java,true)
    }
}