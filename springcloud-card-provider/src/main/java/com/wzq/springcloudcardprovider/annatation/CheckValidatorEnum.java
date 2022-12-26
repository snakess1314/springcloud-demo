package com.wzq.springcloudcardprovider.annatation;

public enum  CheckValidatorEnum {
    Null(1, "为空校验"),
    AGE(2, "年龄校验"),
    Phone(3, "手机校验");

    CheckValidatorEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
