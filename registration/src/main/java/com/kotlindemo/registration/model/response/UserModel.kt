package com.kotlindemo.registration.model.response
data class UserModel(
   val userId: String,
   val name: String,
   val email: String,
   val dialCode: DialCode?,
   val mobileNumber: Long,
   val accessToken: String
)