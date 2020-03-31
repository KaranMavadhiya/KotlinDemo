package com.kotlindemo.registration.api

import com.kotlindemo.registration.model.request.LoginRequestModel
import com.kotlindemo.registration.model.response.UserModel
import com.network.base.BaseRequestModel
import com.network.base.BaseResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkInterceptor {

    // login api
    @POST("v2/auth/login")
    fun callLoginApi(@Body requestBody: BaseRequestModel<LoginRequestModel>): Call<BaseResponseModel<UserModel>>


}