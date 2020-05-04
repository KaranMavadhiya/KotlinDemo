package com.network.base

data class BaseRequestModel<T>(var deviceType: String, var deviceToken: String, var data: T)