package com.chenlong.common.http;

public enum ResultCode {

    SUCCESS(0, "success", true),

    ERROR(1, "error", false),

    LOGIN_ERROR(2, "账号或密码不正确，请重试。", false),

    LOGIN_SUCCESS(3, "登陆成功", true);

    private int code = 0;
    private String description = null;
    private Boolean ok = null;

    ResultCode(int code, String description, Boolean ok) {
        this.code = code;
        this.description = description;
        this.ok = ok;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getOk() {
        return this.ok;
    }

    public static ResultCode get(Integer code) {
        for (ResultCode item : ResultCode.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public static ResultCode get(String description) {
        for (ResultCode item : ResultCode.values()) {
            if (item.getDescription().equals(description)) {
                return item;
            }
        }
        return null;
    }
}
