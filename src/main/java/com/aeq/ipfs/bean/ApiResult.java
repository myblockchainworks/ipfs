package com.aeq.ipfs.bean;

public class ApiResult<T> {

    private Boolean status;
    private T data;
    private String msg;

    public ApiResult() {
    }

    public ApiResult(Boolean status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public ApiResult(Boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
