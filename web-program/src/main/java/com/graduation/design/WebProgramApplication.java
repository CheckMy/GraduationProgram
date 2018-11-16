package com.graduation.design;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xuweizhi
 */
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@EnableScheduling
@MapperScan(basePackages = "com.graduation.design.mapper")
public class WebProgramApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebProgramApplication.class, args);
    }

}
