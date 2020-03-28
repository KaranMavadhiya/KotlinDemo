package com.kotlindemo.registration.ui.login

import android.app.Application
import android.content.Context
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlindemo.base.BaseViewModel
import com.kotlindemo.registration.model.request.LoginRequestModel
import com.kotlindemo.registration.model.response.DialCode
import com.kotlindemo.registration.model.response.UserModel
import com.kotlindemo.utils.LogUtil
import com.kotlindemo.utils.preferences.PreferenceConstant
import com.kotlindemo.utils.preferences.putBoolean
import com.kotlindemo.utils.toMD5


class LoginViewModel(application: Application) : BaseViewModel(application) {

    private val loginRequestModel: LoginRequestModel = LoginRequestModel()

    init {
        loginRequestModel.deviceToken = uniqueId
    }

    var userData = MutableLiveData<UserModel>()

    fun getUserData(): LiveData<UserModel> {
        return userData
    }

    fun setEmail(email: String) {
        loginRequestModel.email = email
    }

    fun setPassword(password: String) {
        loginRequestModel.password = password.toMD5()
    }

    fun apiCallLogin(context: Context) {

        isLoading.value = true

        LogUtil.d("LoginViewModel : ", loginRequestModel.email)
        LogUtil.d("LoginViewModel : ", loginRequestModel.password)
        LogUtil.d("LoginViewModel : ", loginRequestModel.platform)
        LogUtil.d("LoginViewModel : ", loginRequestModel.deviceToken)

        Handler().postDelayed({

            isLoading.value = false

            val dialCode:DialCode = DialCode("India","IN","+91")
            val userModel:UserModel = UserModel("A123","Karan Mavadhiya","Karan.mavadhiya89@gmail.com",dialCode,9998889182,"A123456879XYZ")
            userData.postValue(userModel)

            // set is isUserLogin into Shared Preference after successful login
            PreferenceConstant.isUserLogin.putBoolean(true)

        }, 2000)
    }
}