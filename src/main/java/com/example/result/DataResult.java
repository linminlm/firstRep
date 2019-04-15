package com.example.result;

/**
 * 返回结果统一格式{"status":200,"message":"成功",data:...}
 * @项目：test
 * @author ：linmin
 * @创建时间：2018/1/8
 */
public class DataResult<T> {

    private String status;

    private String message;

    private T data;

    public DataResult() {
    }

    public DataResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public DataResult(String status, String message, T data) {

        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
