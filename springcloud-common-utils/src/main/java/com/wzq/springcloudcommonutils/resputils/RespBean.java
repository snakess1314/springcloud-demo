package com.wzq.springcloudcommonutils.resputils;

public class RespBean {
    private Integer code;
    private String msg;
    private Object data;

    private RespBean() {
    }

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }
    public static RespBean error(Integer code,String msg) {
        return new RespBean(code, msg, null);
    }
    private RespBean(Integer statType, String msg, Object data) {
        this.code = statType;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {

        return code;
    }

    public RespBean setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RespBean setData(Object data) {
        this.data = data;
        return this;
    }

}
