package com.kotlindemo.registration.ui.signup

import android.app.Application
import android.content.Context
import android.os.Handler
import com.kotlindemo.base.BaseViewModel
import com.kotlindemo.registration.model.request.SignupRequestModel
import com.kotlindemo.utils.LogUtil

class SignupViewModel(application: Application) : BaseViewModel(application) {

    val signupRequestModel: SignupRequestModel = SignupRequestModel()

    init {
        signupRequestModel.deviceToken = uniqueId
    }

    fun apiCallSignup(context: Context) {

        isLoading.value = true

        LogUtil.e("LoginViewModel : ", signupRequestModel.name)
        LogUtil.e("LoginViewModel : ", signupRequestModel.email)
        LogUtil.e("LoginViewModel : ", signupRequestModel.dialCode.toString())
        LogUtil.e("LoginViewModel : ", signupRequestModel.mobileNumber)
        LogUtil.e("LoginViewModel : ", signupRequestModel.password)
        LogUtil.e("LoginViewModel : ", signupRequestModel.platform)
        LogUtil.e("LoginViewModel : ", signupRequestModel.deviceToken)

        Handler().postDelayed({ isLoading.value = false }, 2000)
    }
}