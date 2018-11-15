package com.graduation.design.fliter;

import com.graduation.design.service.HelloService;
import com.graduation.design.service.serviceImpl.HelloServiceImpl;

/**
 * @author xuweizhi
 */
public class FilterTest {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        helloService = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        helloService.sayHello("IG牛逼");
    }


}
