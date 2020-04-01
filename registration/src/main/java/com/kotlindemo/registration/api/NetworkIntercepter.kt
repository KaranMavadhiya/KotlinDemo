package com.kotlindemo.registration.api

import com.kotlindemo.registration.model.request.LoginRequestModel
import com.kotlindemo.registration.model.response.UserModel
import com.network.base.BaseRequestModel
import com.network.base.BaseResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkInterceptor {

    /**
     * login api
     *
     * {
     *    "deviceToken": "<DEVICE TOKEN>",
     *    "deviceType": "<PLATFORM>",
     *    "emailId": "<EMAIL ADDRESS>",
     *    "password": "<PASSWORD>"
     * }
     */
    @POST("v1/auth/login")
    fun callLoginApi(@Body requestBody:LoginRequestModel): Call<BaseResponseModel<UserModel>>

    /**
     * login api
     *
     * {
     * "method": "<API METHOD>",
     * "platform": "<PLATFORM>",
     * "data": {
     *    "deviceToken": "<DEVICE TOKEN>",
     *    "deviceType": "<PLATFORM>",
     *    "emailId": "<EMAIL ADDRESS>",
     *    "password": "<PASSWORD>"
     *   }
     * }
     */
    @POST("v1/auth/login")
    fun callLoginApi1(@Body requestBody: BaseRequestModel<LoginRequestModel>): Call<BaseResponseModel<UserModel>>
}