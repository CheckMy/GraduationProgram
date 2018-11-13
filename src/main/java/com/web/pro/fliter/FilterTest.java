package com.web.pro.fliter;

import com.web.pro.service.HelloService;
import com.web.pro.service.serviceImpl.HelloServiceImpl;

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
