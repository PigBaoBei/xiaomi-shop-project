package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {

    private String code;

    private String msg;

    private Object data;


    public static JsonResult success(String msg, Object data){
        return new JsonResult("0", msg, data);
    }

    public static JsonResult success(String msg){
        return new JsonResult("0", msg, null);
    }


    public static JsonResult error(String msg){
        return new JsonResult("-1",msg,null);
    }

    public static JsonResult error(String code,String msg, Object data){
        return new JsonResult(code,msg,data);
    }
    public static JsonResult error(){
        return new JsonResult("-1","操作失败",null);
    }

    public static JsonResult error(String msg, Object data){
        return new JsonResult("-1", msg,data);
    }

}
