package com.kotlindemo.registration.model.request

import com.kotlindemo.registration.utils.Constants

class LoginRequestModel {
    lateinit var emailId: String
    lateinit var password: String
    var deviceType = Constants.DEVICE_TYPE
    lateinit var deviceToken: String
}