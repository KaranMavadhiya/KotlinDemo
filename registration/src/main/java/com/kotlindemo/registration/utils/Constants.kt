package com.kotlindemo.registration.utils

import com.kotlindemo.registration.BuildConfig

object Constants {

    /**
     * Device type
     */
    const val DEVICE_TYPE = "Android"

    /**
     * BASE URL
     */
    const val BASE_URL = BuildConfig.API_BASE_URL

    /**
     * NETWORK SUCCESS FAILURE CONSTANTS
     */
    const val SUCCESS = 1
    const val FAILURE = 0

    const val STATUS_CODE_SUCCESS = 200


}