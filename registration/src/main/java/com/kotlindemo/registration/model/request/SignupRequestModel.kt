package com.kotlindemo.registration.model.request

import com.kotlindemo.registration.utils.Constants

class SignupRequestModel {
    lateinit var name: String
    lateinit var email: String
    var dialCode = DialCode()
    lateinit var mobileNumber: String
    lateinit var password: String
    var deviceType = Constants.DEVICE_TYPE
    lateinit var deviceToken: String

    class DialCode {
        lateinit var countryName : String
        lateinit var countryDialCode : String
        lateinit var countryCode : String
    }
}