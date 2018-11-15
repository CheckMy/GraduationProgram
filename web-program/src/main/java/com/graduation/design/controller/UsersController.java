package com.graduation.design.controller;


import com.graduation.design.config.redis.ListRedisTest;
import com.graduation.design.config.redis.RedisTemplateImple;
import com.graduation.design.config.redis.StringRedisTest;
import com.graduation.design.model.Users;
import com.graduation.design.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2018/11/13 16:21
 */
@RestController
@Slf4j
@RequestMapping("/index")
public class UsersController {

    @Autowired
    private Users users;

    @Autowired
    private RedisTemplateImple redisService;

    @Autowired
    private StringRedisTest redisTest;

    @Autowired
    private ListRedisTest listRedisTest;

    @GetMapping("/index")
    public Object getIndex() {
        log.info("format={}", users.toString());
        return users;
    }

    @GetMapping("/redis")
    public List<User> saveUsers() {
        User user1 = new User(1, "Jack", "121", "xx", "xxx", 1, new Date(), "123");
        User user2 = new User(2, "Jack", "121", "xx", "xxx", 1, new Date(), "123");
        User user3 = new User(3, "Jack", "121", "xx", "xxx", 1, new Date(), "123");
        User user4 = new User(4, "Jack", "121", "xx", "xxx", 1, new Date(), "123");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        redisService.saveUserWithRedis(users);
        return users;
    }

    @GetMapping("/list")
    public String list() {
        redisService.listRedis();
        return "success";
    }

    @GetMapping("/string")
    public String stringRedis() {
        redisTest.testSet();
        return "success";
    }

    @GetMapping("/lists")
    public String lists() {
        listRedisTest.testSet();
        return "success";
    }

}
