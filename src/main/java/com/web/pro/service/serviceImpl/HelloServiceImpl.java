package com.web.pro.service.serviceImpl;

import com.web.pro.service.HelloService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        if ("Mike".equals(name)) {
            throw new RuntimeException("对不起，我出小差了");
        }
        System.out.println("非常开心看到你！");
    }

    @Override
    public void hello() {

    }

}
