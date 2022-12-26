package com.wzq.springcloudcommonutils.resputils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RespBeanResult implements Serializable {
    private String result;
    private Object data;
    private String success;
    private RowPage rowPage;
    private List<Error> errors;

    public static RespBeanResult ok(){
        return createResult("ok",null,"成功...",null,null);
    }
    public static RespBeanResult ok(Object data){
        return createResult("ok",data,"成功...",null,null);
    }
    public static RespBeanResult notOk(List<Error> errors){
        return createResult("not_ok",null,"",null,errors);
    }
    private static RespBeanResult createResult(String result,Object data,String success,RowPage rowPage,List<Error> errors){
        RespBeanResult resp=new RespBeanResult();
        resp.setResult(result);
        resp.setData(data);
        resp.setSuccess(success);
        resp.setRowPage(rowPage);
        resp.setErrors(errors);
        return resp;
    }

    @Data
    public static class RowPage{
        private int total;
        private int offset;
        private int limt;

    }
    @Data
    @AllArgsConstructor
    public static class Error{
        private String field;
        private String msg;
    }
}
