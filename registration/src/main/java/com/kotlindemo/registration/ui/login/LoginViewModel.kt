package com.kotlindemo.registration.ui.login

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlindemo.base.BaseViewModel
import com.kotlindemo.registration.api.NetworkInstance
import com.kotlindemo.registration.api.NetworkInterceptor
import com.kotlindemo.registration.model.request.LoginRequestModel
import com.kotlindemo.registration.model.response.UserModel
import com.kotlindemo.registration.utils.Constants
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

    fun apiCallLogin(context: Context) {
        isLoading.value = true

        // val requestModel = BaseRequestModel(Constants.DEVICE_TYPE, loginRequestModel.deviceToken, loginRequestModel)

        NetworkInstance.getNetworkInterceptor().callLoginApi(loginRequestModel).enqueue(object :
        retrofit2.Callback<BaseResponseModel<UserModel>> {

            override fun onFailure(call: Call<BaseResponseModel<UserModel>>, t: Throwable) {
                isLoading.value = false
            }

            override fun onResponse(call: Call<BaseResponseModel<UserModel>>, response: Response<BaseResponseModel<UserModel>>) {
                isLoading.value = false
                if (response.body()?.status == Constants.SUCCESS && response.body()?.statusCode == Constants.STATUS_CODE_SUCCESS) {
                    val responseModel =  response.body()?.data
                    userData.value = responseModel
                }else{
                    Toast.makeText(context, response.body()?.message,Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}