package com.example.entity.nosql;

import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * api计数器的接口存于mongodb
 * @项目：test
 * @author ：linmin
 * @创建时间：2018/1/11
 */
public class ApiMonitorInfo implements Serializable {

    private static final long serialVersionUID = -3258839839160856613L;
    private ObjectId _id;

    /**
     * 用户IP
     */
    private String userIP;

    /**
     * 调用的方法名
     */
    private String methodName;

    /**
     * 所在类
     */
    private String controllerName;

    /**
     * 调用次数
     */
    private Long counter;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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
