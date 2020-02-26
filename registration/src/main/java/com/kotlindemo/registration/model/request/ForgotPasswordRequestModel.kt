package com.kotlindemo.registration.model.request

import com.kotlindemo.utils.Constants

class ForgotPasswordRequestModel {

    lateinit var email: String

    var platform = Constants.PLATFORM

    lateinit var deviceToken: String

}