package com.graduation.design.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author xuweizhi
 * @date 2018/11/13 16:38
 */
//@Aspect
//@Component
//@Order(3)
//@Slf4j
public class ControllerAop2 {

    /*//@Pointcut("execution(public * com.web.pro.controller.*.*(..))")
    public void pointCut() {
    }

    //@Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("Before Advice={}", "前置通知2");
    }

    //@After("pointCut()")
    public void after() {
        log.info("After Advice={}", "2");
    }
*/
}
