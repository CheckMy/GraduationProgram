package com.graduation.design.controller;


import com.graduation.design.annotatoin.UrlLogRequired;
import com.graduation.design.model.User;
import com.graduation.design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = new User("我们真是一个好人", "123456", "123456", "123456", 1, new Date(), "xx");
        return userService.insert(user);
    }

    @UrlLogRequired(value = "查询单个数据", isLog = true)
    @GetMapping("/get")
    public User getUser(Integer id) {
        return userService.findById(id);
    }

    @UrlLogRequired(value = "更新数据", isLog = true)
    @GetMapping("/update")
    public User update(String name, Integer id) {
        User byId = userService.getById(id);
        byId.setUsername(name);
        return userService.update(byId);
    }

    @UrlLogRequired(value = "查询单个数据", isLog = true)
    @GetMapping("/delete")
    public void delete(Integer id) {
        User user = new User();
        user.setId(id);
        userService.delete(user);
    }

    @GetMapping("/getPlus")
    public User getPlus(Integer id) {
        return userService.getById(id);
    }

}
