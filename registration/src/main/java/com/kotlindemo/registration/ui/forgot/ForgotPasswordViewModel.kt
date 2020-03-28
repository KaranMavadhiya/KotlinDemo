package com.kotlindemo.registration.ui.forgot

import android.app.Application
import android.content.Context
import android.os.Handler
import com.kotlindemo.base.BaseViewModel
import com.kotlindemo.registration.model.request.ForgotPasswordRequestModel
import com.kotlindemo.utils.LogUtil

class ForgotPasswordViewModel(application: Application) : BaseViewModel(application) {

    private val forgotPasswordRequestModel: ForgotPasswordRequestModel = ForgotPasswordRequestModel()

    init {
        forgotPasswordRequestModel.deviceToken = uniqueId
    }

    fun setEmail(email: String) {
        forgotPasswordRequestModel.email = email
    }

    fun apiCallForgotPassword(context: Context) {

        isLoading.value = true

        LogUtil.e("LoginViewModel : ", forgotPasswordRequestModel.email)
        LogUtil.e("LoginViewModel : ", forgotPasswordRequestModel.platform)
        LogUtil.e("LoginViewModel : ", forgotPasswordRequestModel.deviceToken)

        Handler().postDelayed({ isLoading.value = false }, 2000)
    }
}