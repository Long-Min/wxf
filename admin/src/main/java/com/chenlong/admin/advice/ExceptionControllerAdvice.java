package com.chenlong.admin.advice;

import com.chenlong.common.http.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//SpringMVC中获取全局异常然后请求的方法,能处理相关异常
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class) //标识当前方法是错误异常处理方法
    @ResponseBody //返回消息体(ajax的返回值)
    public Result<?> handleBusinessException(HttpServletRequest request, Exception e) {
        return Result.error(e.getMessage());
    }

}
