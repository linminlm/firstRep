package com.example.exception;

import com.example.Enum.UserEnum;

/**
 * @项目：test
 * @author ：linmin
 * @创建时间：2018/1/9
 */
public class UserException extends RuntimeException {

    private String status;

    public UserException(UserEnum userEnum) {
        super(userEnum.getMessage());
        this.status = userEnum.getStatus();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
