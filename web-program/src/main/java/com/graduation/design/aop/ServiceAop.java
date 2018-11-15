package com.graduation.design.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @date 2018/11/15 8:59
 */
@Component
@Aspect
@Order(2)
@Slf4j
public class ServiceAop {

    /**
     * 保证每个线程都有一个单独的实例
     */
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    private boolean flag = false;

    /**
     * 横切所有的cont方法
     */
    @Pointcut("execution(* com.graduation.design.service.serviceImpl.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        threadLocal.set(System.currentTimeMillis());
         UrlControllerAop.otherLogPrint(joinPoint);
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("class:"+joinPoint.getTarget().getClass().getName()+"; method:"+joinPoint.getSignature().getName()+"耗时 :{}",
                ((System.currentTimeMillis() - threadLocal.get())) + "ms");
        threadLocal.remove();
    }


}
