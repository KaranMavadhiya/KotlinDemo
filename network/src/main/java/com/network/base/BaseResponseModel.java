package com.network.base;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public class BaseResponseModel<B> extends ResponseModel{


    B ResponseJson;

    @Expose
    @SerializedName("data")
    Object data;

    private static Gson gson;

    public Object getData() {
        return data;
    }

    public B getResponseModel(Class<B> aModel) {
        setResponseJson(aModel);
        return ResponseJson;
    }

    public B getResponseModel(Type typeOfObjectsList){
        return new Gson().fromJson(getGsonWithExpose().toJson(data), typeOfObjectsList);
    }

    public void setResponse(String data) {
        this.data = data;
    }

    private void setResponseJson(Class<B> aModel) {
        ResponseJson = prepareModel(data, aModel);
    }

    private <T> T prepareModel(@NonNull Object aString, Class<T> aClass) {
        return new Gson().fromJson(new Gson().toJson(aString), aClass);
    }

    private static Gson getGsonWithExpose() {
        if (gson != null) {
            gson = null;
        }
        gson = new GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create();
        return gson;
    }
}
