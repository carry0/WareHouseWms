package com.yf.network.callback;

import com.google.gson.annotations.SerializedName;

/**
 * @Author
 * @cerate 2021/9/2 14:41
 **/
public class BaseRequest<T> {
    private int code;
    @SerializedName("msg")
    private String message;

    private T data;

    private int type;

    public BaseRequest(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
