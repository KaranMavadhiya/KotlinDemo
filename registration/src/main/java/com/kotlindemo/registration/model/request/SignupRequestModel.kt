package com.kotlindemo.registration.model.request

import com.kotlindemo.utils.Constants

class SignupRequestModel {

    lateinit var name: String

    lateinit var email: String

    var dialCode = DialCode()

    lateinit var mobileNumber: String

    lateinit var password: String

    var platform = Constants.PLATFORM

    lateinit var deviceToken: String

    class DialCode {

        lateinit var countryCode : String

        lateinit var dialCode : String

        lateinit var countryName : String
    }
}