package com.kotlindemo.registration.ui.login

import android.app.Application
import android.content.Context
import android.os.Handler
import com.kotlindemo.base.BaseViewModel
import com.kotlindemo.registration.model.request.LoginRequestModel
import com.kotlindemo.utils.LogUtil
import com.kotlindemo.utils.preferences.PreferenceConstant
import com.kotlindemo.utils.preferences.putBoolean
import com.kotlindemo.utils.toMD5


class LoginViewModel(application: Application) : BaseViewModel(application) {

    private val loginRequestModel: LoginRequestModel = LoginRequestModel()

    init {
        loginRequestModel.deviceToken = uniqueId
    }

    fun setEmail(email: String) {
        loginRequestModel.email = email
    }

    fun setPassword(password: String) {
        loginRequestModel.password = password.toMD5()
    }

    fun apiCallLogin(context: Context) {

        isLoading.value = true

        LogUtil.e("LoginViewModel : ", loginRequestModel.email)
        LogUtil.e("LoginViewModel : ", loginRequestModel.password)
        LogUtil.e("LoginViewModel : ", loginRequestModel.platform)
        LogUtil.e("LoginViewModel : ", loginRequestModel.deviceToken)

        Handler().postDelayed({ isLoading.value = false }, 3000)

        // set is isUserLogin into Shared Preference after successful login
        // PreferenceConstant.isUserLogin.putBoolean(true)

    }
}