package com.laomukuq.entity;

import com.alibaba.fastjson.JSONObject;

public class HttpResponseEntity {
    private int code;
    private String message;
    private JSONObject responseEntity;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(JSONObject responseEntity) {
        this.responseEntity = responseEntity;
    }

}
