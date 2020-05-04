package com.kotlindemo.registration.ui.forgot

import android.app.Application
import android.content.Context
import android.os.Handler
import com.kotlindemo.base.BaseViewModel
import com.kotlindemo.registration.model.request.ForgotPasswordRequestModel
import com.kotlindemo.utils.LogUtil

class ForgotPasswordViewModel(application: Application) : BaseViewModel(application) {
    private var tag = ForgotPasswordViewModel::class.java.simpleName

    val forgotPasswordRequestModel: ForgotPasswordRequestModel = ForgotPasswordRequestModel()

    init {
        forgotPasswordRequestModel.deviceToken = uniqueId
    }

    fun apiCallForgotPassword(context: Context) {

        isLoading.value = true

        LogUtil.e(tag, forgotPasswordRequestModel.email)
        LogUtil.e(tag, forgotPasswordRequestModel.deviceType)
        LogUtil.e(tag, forgotPasswordRequestModel.deviceToken)

        Handler().postDelayed({ isLoading.value = false }, 2000)
    }
}