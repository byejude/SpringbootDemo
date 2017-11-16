package com.tulip.girlTest.handle;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AuthExceptionHandle {
    private Logger logger= LoggerFactory.getLogger(GirlExceptionHandle.class);


    @ExceptionHandler(value= AuthenticationException.class)
    @ResponseBody
    public void handle(){}

}