package com.web.pro.annotatoin;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @date 2018/11/15 8:50
 */
//表示需要在什么级别保存该注解信息。可选的RetentionPolicy参数包括：
//SOURCE：注解将被编译器丢弃
//CLASS：注解在class文件中可用，但会被VM丢弃
//RUNTIME：VM将在运行期间保留注解，因此可以通过反射机制读取注解的信息。
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UrlLogRequired {

    /**
     * 业务名称，或者方法名称
     */
    String value() default "";

    /**
     * 是否需要打印方法日志信息，默认打印
     */
    boolean isLog() default true;

}
