package com.example.spring.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public Result() {

    }


    public static <T> Result<T> success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("success");
        return result;
    }
    public static <T> Result<T> success(T data) {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("success");
        return result;
    }

    public static <T> Result<T> error(String code, String message) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }
}