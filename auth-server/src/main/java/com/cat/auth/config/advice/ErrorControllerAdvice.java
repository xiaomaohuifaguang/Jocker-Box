package com.cat.auth.config.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/***
 * 接口异常处理类
 * @title ErrorControllerAdvice
 * @description 接口异常处理类
 * @author xiaomaohuifaguang
 * @create 2024/6/19 23:50
 **/
@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String error(Exception e){
        e.printStackTrace();
        String error = "错误";
        if(e instanceof org.springframework.web.servlet.NoHandlerFoundException){
            error = "404";
        }else {
            error = e.getMessage();
        }
        return error;
    }
}
