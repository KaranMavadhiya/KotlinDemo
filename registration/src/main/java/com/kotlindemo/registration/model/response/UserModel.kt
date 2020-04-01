package com.kotlindemo.registration.model.response

data class UserModel(
    val _id: String,
    val name: String,
    val emailId: String,
    val dialCode: DialCode?,
    val mobileNumber: Long,
    val accessToken: String
)