package com.kotlindemo.registration.model.request

import com.kotlindemo.utils.Constants

class LoginRequestModel {

    lateinit var emailId: String

    lateinit var password: String

    var deviceType = Constants.PLATFORM

    lateinit var deviceToken: String

}