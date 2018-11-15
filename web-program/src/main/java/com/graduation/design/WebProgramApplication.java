package com.graduation.design;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xuweizhi
 */
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@MapperScan(basePackages = "com.graduation.design.mapper")
public class WebProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebProgramApplication.class, args);
    }
}
