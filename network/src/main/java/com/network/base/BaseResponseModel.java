package com.network.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class BaseResponseModel<B> extends ResponseModel{

    @SerializedName("data")
    private Object data;

    private static Gson gson;
    private B DataResponseJson;

    public Object getData() {
        return data;
    }

    public B getResponseData(Class<B> aModel) {
        setResponseJson(aModel);
        return DataResponseJson;
    }

    public void setResponseData(String data) {
        this.data = data;
    }

    private void setResponseJson(Class<B> aModel) {
        DataResponseJson = prepareModel(data, aModel);
    }

    private <T> T prepareModel(Object aString, Class<T> aClass) {
        return getGsonWithExpose().fromJson(getGsonWithExpose().toJson(aString), aClass);
    }

    private static Gson getGsonWithExpose() {
        if (gson != null) {
            gson = null;
        }
        gson = new GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create();
        return gson;
    }
}
