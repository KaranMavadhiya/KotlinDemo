package com.kotlindemo.registration.model.request

import com.kotlindemo.registration.utils.Constants
import com.squareup.moshi.Json

class SignupRequestModel {
    @Json(name = "name")
    lateinit var name: String

    @Json(name = "emailId")
    lateinit var email: String

    @Json(name = "dialCode")
    var dialCode = DialCode()

    @Json(name = "mobileNumber")
    lateinit var mobileNumber: String

    @Json(name = "password")
    lateinit var password: String

    @Json(name = "deviceType")
    var deviceType = Constants.DEVICE_TYPE

    @Json(name = "deviceToken")
    lateinit var deviceToken: String

    class DialCode {
        @Json(name = "countryName")
        lateinit var countryName : String

        @Json(name = "countryDialCode")
        lateinit var countryDialCode : String

        @Json(name = "countryCode")
        lateinit var countryCode : String
    }
}