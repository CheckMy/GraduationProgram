package com.web.pro.controller;

import com.web.pro.model.User;
import com.web.pro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author xuweizhi
 * @date 2018/11/14 15:59
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/save")
    public User save() {
        User user = new User("", "123456", "123456", "123456", 1, new Date(), "xx");
        userService.save(user);
        return user;
    }
}
