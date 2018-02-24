package com.example.Enum;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/2/7
 * @公司：汽车易生活
 */
public enum ShiroEnum {
    LOGIN_FAIL("401","密码或者用户名错误"),
    NOT_AUTH("403","没有权限"),
    NOT_LOGIN("403","请先登录"),
    LOGIN_TIMEOUT("403","登录超时，请先登录"),
    UNKNOWN_FAIL("-1","未知错误");

    private String status;
    private String message;

    private ShiroEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
