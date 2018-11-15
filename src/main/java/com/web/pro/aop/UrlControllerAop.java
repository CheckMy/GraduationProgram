package com.web.pro.aop;

import com.web.pro.annotatoin.UrlLogRequired;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author xuweizhi
 * @date 2018/11/15 8:59
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class UrlControllerAop {

    /**
     * 保证每个线程都有一个单独的实例
     */
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    private boolean flag = false;

    /**
     * 横切所有的cont方法
     */
    @Pointcut("execution(* com.web.pro.controller.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        threadLocal.set(System.currentTimeMillis());

        //获取拦截的方法名
        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            log.error("该注解只能用于方法");
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        UrlLogRequired urlLogRequired = currentMethod.getAnnotation(UrlLogRequired.class);
        if (urlLogRequired != null && urlLogRequired.isLog()) {
            this.flag = urlLogRequired.isLog();
            log.info("方法描述", urlLogRequired.value());
            logPrint(joinPoint);
        }
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        if (flag)
            log.info("after():{}", joinPoint.toString());
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        if (flag)
            log.info("Class:" + joinPoint.getTarget().getClass().getSimpleName() + "耗时 :{}", ((System.currentTimeMillis() - threadLocal.get())) + "ms");
        this.flag = false;
        threadLocal.remove();
    }

    public void logPrint(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("Request URL: {}", request.getRequestURL().toString());
        log.info("Request Method: {}", request.getMethod());
        /*log.info("IP: {}", request.getRemoteAddr());
        log.info("User-Agent:{}", request.getHeader("User-Agent"));
        log.info("Cookies:{}", request.getCookies());*/
        log.info("Class Method:{}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //log.info("Params:{}", Arrays.toString(joinPoint.getArgs()));
        Enumeration<String> enums = request.getParameterNames();
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        while (enums.hasMoreElements()) {
            String paraName = enums.nextElement();
            sb.append(paraName + ": " + request.getParameter(paraName) + ", ");
        }
        log.info("params {}", sb.toString().substring(0, sb.length() - 2) + "}");
    }

    public static void otherLogPrint(JoinPoint joinPoint) {
        log.info("Interface:{}", joinPoint.getTarget().getClass().getInterfaces()[0].getSimpleName());
        log.info("Method: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }
}
