package com.kotlindemo.registration.model.request

import com.kotlindemo.registration.utils.Constants
import com.squareup.moshi.Json

class LoginRequestModel {
    @Json(name = "emailId")
    lateinit var emailId: String

    @Json(name = "password")
    lateinit var password: String

    @Json(name = "deviceType")
    var deviceType = Constants.DEVICE_TYPE

    @Json(name = "deviceToken")
    lateinit var deviceToken: String
}