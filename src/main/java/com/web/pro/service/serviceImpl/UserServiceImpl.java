package com.web.pro.service.serviceImpl;

import com.web.pro.mapper.UserMapper;
import com.web.pro.model.User;
import com.web.pro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xuweizhi
 * @date 2018/11/14 16:01
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User save(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
