package com.kotlindemo.registration.api

import com.network.retrofit.RetrofitClientFactory

object APIHelper {
    fun getInstance() : NetworkInterceptor {
       return RetrofitClientFactory.getInstance(NetworkInterceptor::class.java)
    }
}