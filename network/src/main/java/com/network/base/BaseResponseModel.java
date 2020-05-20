package com.network.base;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;


public class BaseResponseModel<B> extends ResponseModel{

    @SerializedName("data") // @Json(name="data")
    private JsonObject data;

    public B getResponseModel(Class<B> aClass) {
        return prepareModel(data, aClass);
    }

    private <T> T prepareModel(@NonNull JsonObject aString, Class<T> aClass) {
        return new Gson().fromJson(aString, aClass);
    }
}
