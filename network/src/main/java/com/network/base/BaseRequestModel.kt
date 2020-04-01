package com.network.base

data class BaseRequestModel<T>(var method: String, var platform: String, var data: T)