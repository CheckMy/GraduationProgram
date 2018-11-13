package com.web.pro.fliter;

import java.lang.reflect.InvocationTargetException;

public class MyInterceptor implements Interceptor {

    @Override
    public boolean before() {
        System.out.println("方法执行前！");
        return  true;
    }

    @Override
    public void after() {
        System.out.println("方法执行后！");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Around之前");
        Object object = invocation.proceed();
        System.out.println(object);
        return null;
    }

    @Override
    public void afterReturning() {
        System.out.println("方法返回前");
    }

    @Override
    public void afterThrowing() {
        System.out.println("跑出异常之前！");
    }

    @Override
    public boolean userAround() {
        System.out.println("使用这个切面");
        return false;
    }

}
