package com.kotlindemo.registration.model.request

import com.kotlindemo.registration.utils.Constants


class ForgotPasswordRequestModel {
    lateinit var email: String
    var deviceType = Constants.DEVICE_TYPE
    lateinit var deviceToken: String
}