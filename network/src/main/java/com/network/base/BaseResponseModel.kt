package com.network.base
import com.squareup.moshi.Json

class BaseResponseModel<T> {
    @Json(name = "status")
    var status = 0

    @Json(name = "message")
    var message: String? = null

    @Json(name = "statusCode")
    var statusCode = 0

    @Json(name = "data")
    var data: T? = null
}