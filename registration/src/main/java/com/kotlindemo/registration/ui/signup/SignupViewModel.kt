package com.kotlindemo.registration.ui.signup

import android.app.Application
import android.content.Context
import android.os.Handler
import com.kotlindemo.base.BaseViewModel
import com.kotlindemo.registration.model.request.SignupRequestModel
import com.kotlindemo.utils.LogUtil

class SignupViewModel(application: Application) : BaseViewModel(application) {
    private var tag = SignupViewModel::class.java.simpleName

    val signupRequestModel: SignupRequestModel = SignupRequestModel()

    init {
        signupRequestModel.deviceToken = uniqueId
    }

    fun apiCallSignup(context: Context) {

        isLoading.value = true

        LogUtil.e(tag, signupRequestModel.name)
        LogUtil.e(tag, signupRequestModel.email)
        LogUtil.e(tag, signupRequestModel.dialCode.toString())
        LogUtil.e(tag, signupRequestModel.mobileNumber)
        LogUtil.e(tag, signupRequestModel.password)
        LogUtil.e(tag, signupRequestModel.deviceType)
        LogUtil.e(tag, signupRequestModel.deviceToken)

        Handler().postDelayed({ isLoading.value = false }, 2000)
    }
}