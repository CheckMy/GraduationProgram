package com.graduation.design.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.design.model.User;

import java.util.List;

/**
 * @author xuweizhi
 * @date 2018/11/14 16:00
 */
public interface UserService extends IService<User> {

    public void delete(User user);

    public User update(User user);

    public User findById(Integer id);

    public List<User> findAll();

    public User insert(User user);
}
