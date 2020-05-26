package com.kotlindemo.registration.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserModel(
    @Json(name = "accessToken")
    var accessToken: String = "",
    @Json(name = "emailId")
    var emailId: String = "",
    @Json(name = "_id")
    var id: String = "",
    @Json(name = "mobileNumber")
    var mobileNumber: Long = 0,
    @Json(name = "name")
    var name: String = ""
)