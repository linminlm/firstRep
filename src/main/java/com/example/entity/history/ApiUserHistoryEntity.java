package com.example.entity.history;

import javax.persistence.*;
import java.util.Date;

/**
 * @项目：test
 * @author ：linmin
 * @创建时间：2018/1/16
 */

@Entity
@Table(name="api_user_history")
public class ApiUserHistoryEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="user_ip")
    private String userIP;

    @Column(name="controller_name")
    private String controllerName;

    @Column(name="method_name")
    private String methodName;

    @Column(name="last_date")
    private Date lastDate;

    @Column(name="pre_date")
    private Date preDate;

    @Column(name="pre_num")
    private Long preNum;

    @Column(name="last_num")
    private Long lastNum;

    @Column(name="total_num")
    private Long totalNum;

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

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getPreDate() {
        return preDate;
    }

    public void setPreDate(Date preDate) {
        this.preDate = preDate;
    }

    public Long getPreNum() {
        return preNum;
    }

    public void setPreNum(Long preNum) {
        this.preNum = preNum;
    }

    public Long getLastNum() {
        return lastNum;
    }

    public void setLastNum(Long lastNum) {
        this.lastNum = lastNum;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }
}
