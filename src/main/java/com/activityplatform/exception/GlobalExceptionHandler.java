package com.activityplatform.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.activityplatform.common.RetJson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.activityplatform.controller")
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.get();

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RetJson error(HttpServletRequest request, Exception e){
        log.error("异常信息", e);
        return RetJson.fail(-8, "系统异常");
    }

    //test
    @ExceptionHandler(ActivityException.class)
    @ResponseBody
    public RetJson customError(HttpServletRequest request, ActivityException e){
        return RetJson.fail(Integer.parseInt(e.getCode()), e.getMsg());
    }
}
