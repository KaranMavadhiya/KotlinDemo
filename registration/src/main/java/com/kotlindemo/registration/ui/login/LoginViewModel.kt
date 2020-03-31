package com.kotlindemo.registration.ui.login

import android.app.Application
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlindemo.base.BaseViewModel
import com.kotlindemo.registration.api.APIHelper
import com.kotlindemo.utils.LogUtil
import com.kotlindemo.utils.preferences.PreferenceConstant
import com.kotlindemo.utils.preferences.putBoolean
import com.kotlindemo.registration.model.request.LoginRequestModel
import com.kotlindemo.registration.model.response.DialCode
import com.kotlindemo.registration.model.response.UserModel
import com.network.base.BaseRequestModel
import com.network.base.BaseResponseModel
import retrofit2.Callback

class LoginViewModel(application: Application) : BaseViewModel(application) {

    val loginRequestModel: LoginRequestModel = LoginRequestModel()

    init {
        loginRequestModel.deviceToken = uniqueId
    }

    var userData = MutableLiveData<UserModel>()

    fun getUserData(): LiveData<UserModel> {
        return userData
    }

    fun apiCallLogin() {
        isLoading.value = true

        val requestModel = BaseRequestModel(loginRequestModel)


        LogUtil.d("LoginViewModel : ", loginRequestModel.email)
        LogUtil.d("LoginViewModel : ", loginRequestModel.password)
        LogUtil.d("LoginViewModel : ", loginRequestModel.platform)
        LogUtil.d("LoginViewModel : ", loginRequestModel.deviceToken)

        Handler().postDelayed({

            isLoading.value = false

            val dialCode: DialCode = DialCode("India","IN","+91")
            val userModel: UserModel = UserModel("A123","Karan Mavadhiya","Karan.mavadhiya89@gmail.com",dialCode,9998889182,"A123456879XYZ")
            userData.postValue(userModel)

            // set is isUserLogin into Shared Preference after successful login
            PreferenceConstant.isUserLogin.putBoolean(true)

        }, 2000)
    }
}