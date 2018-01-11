package com.example.entity.nosql;

import java.io.Serializable;

/**
 * api计数器的接口存于mongodb
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/11
 * @公司：汽车易生活
 */
public class ApiMonitorInfo implements Serializable {

    private static final long serialVersionUID = -3258839839160856613L;
    private Long id;

    //用户IP
    private String userIP;

    //调用的方法名
    private String methodName;

    //调用的方法所在控制器
    private String controllerName;

    //调用次数
    private Long counter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }
}
