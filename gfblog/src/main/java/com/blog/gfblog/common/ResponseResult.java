package com.blog.gfblog.common;





import com.blog.gfblog.enump.ResponseEnum;
import io.swagger.models.auth.In;

import java.io.Serializable;

/**
 * 请求结果集合
 * @param <T>
 */
public class ResponseResult<T> implements Serializable {
    private int code;
    private String msg;
    private T data;
  public static ResponseResult Success(){
      return new ResponseResult(ResponseEnum.FIND_SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(),null);
  }

  public static  ResponseResult error(){
      return new ResponseResult(500,"服务器内部错误");
  }

    public static  ResponseResult error(String errorMsg){
        return new ResponseResult(500,errorMsg);
    }

    public static  ResponseResult error(Integer errorCode, String errorMsg){
        return new ResponseResult(errorCode,errorMsg);
    }

    public static ResponseResult Success(Object data){
        return new ResponseResult(200, "成功",data);
    }

    public static ResponseResult Fail(){
        return new ResponseResult(ResponseEnum.FAIL.getCode(), ResponseEnum.FAIL.getMsg(),null);
    }
    public static ResponseResult SuccessResponseData(Object data){
        return new ResponseResult(ResponseEnum.FIND_SUCCESS.getCode(), ResponseEnum.FIND_SUCCESS.getMsg(),data);
    }
    public static ResponseResult elevotorSuccess(){
        return new ResponseResult(ResponseEnum.FIND_SUCCESS.getCode(), ResponseEnum.FIND_SUCCESS.getMsg(),null);
    }
    public static ResponseResult elevotorFail(){
        return new ResponseResult(ResponseEnum.FAIL.getCode(),ResponseEnum.FAIL.getMsg(), null);
    }
    public static ResponseResult elevotorSuccessResponseData(Object data){
        return new ResponseResult(ResponseEnum.FIND_SUCCESS.getCode(), ResponseEnum.FIND_SUCCESS.getMsg(),data);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
