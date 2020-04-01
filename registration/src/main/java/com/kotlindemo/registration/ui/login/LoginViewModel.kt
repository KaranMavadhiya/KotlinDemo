package com.kotlindemo.registration.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlindemo.base.BaseViewModel
import com.kotlindemo.registration.api.APIHelper
import com.kotlindemo.registration.model.request.LoginRequestModel
import com.kotlindemo.registration.model.response.UserModel
import com.kotlindemo.utils.LogUtil
import com.network.base.BaseResponseModel
import retrofit2.Call
import retrofit2.Response

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

        // val requestModel = BaseRequestModel("login",Constants.PLATFORM,loginRequestModel)

        APIHelper.getInstance().callLoginApi(loginRequestModel).enqueue(object :
        retrofit2.Callback<BaseResponseModel<UserModel>> {

            override fun onFailure(call: Call<BaseResponseModel<UserModel>>, t: Throwable) {
                isLoading.value = false
            }

            override fun onResponse(call: Call<BaseResponseModel<UserModel>>, response: Response<BaseResponseModel<UserModel>>) {
                isLoading.value = false
                if (response.body()?.status == 1) {
                    val responseModel =  response.body()?.getResponseData(UserModel::class.java)
                    userData.value = responseModel
                }else{
                    response.body()?.message?.let { LogUtil.e("LOGIN", it) }
                }
            }
        })
    }
}