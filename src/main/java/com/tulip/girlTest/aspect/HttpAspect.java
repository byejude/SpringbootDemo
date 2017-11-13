package com.tulip.girlTest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.tulip.girlTest.controller.GirlController.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("111111111111111");
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        logger.info("url={}",request.getRequestURL());

        logger.info("method={}",request.getMethod());

        logger.info("ip={}",request.getRemoteAddr());

        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"/"+joinPoint.getSignature().getName());

        logger.info("args={}",joinPoint.getArgs());
    }


    @After("log()")
    public void doAfter(){
        logger.info("222222222222");
    }

    //获取返回后的类容
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("3333333333333");
        logger.info("response={}", object.toString());
    }
}
