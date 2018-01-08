package com.example.Enum;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/1/8
 * @公司：汽车易生活
 */
public enum UserEnum {
    SUCCESS("200","操作成功"),
    DEL_FAIL("500","删除失败"),
    ADD_FAIL("500","添加失败"),
    UPD_FAIL("500","修改失败"),
    SEL_FAIL("500","查询失败");

    private String status;
    private String message;

    private UserEnum(String status, String message) {
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
