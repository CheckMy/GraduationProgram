package com.web.pro.controller;

import com.web.pro.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/index")
    public Object getIndex() {
        log.info("format={}", users.toString());
        return users;
    }
}
