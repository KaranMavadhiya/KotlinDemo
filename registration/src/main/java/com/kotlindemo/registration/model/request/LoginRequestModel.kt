package com.kotlindemo.registration.model.request

import com.kotlindemo.utils.Constants

class LoginRequestModel {

    lateinit var email: String

    lateinit var password: String

    var platform = Constants.PLATFORM

    lateinit var deviceToken: String

}