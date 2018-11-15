package com.graduation.dd.service.impl;

import com.graduation.dd.model.User;
import com.graduation.dd.mapper.UserMapper;
import com.graduation.dd.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuweizhi
 * @since 2018-11-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
