package com.graduation.design.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.design.mapper.UserMapper;
import com.graduation.design.model.User;
import com.graduation.design.model.UserExample;
import com.graduation.design.service.BaseService;
import com.graduation.design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author xuweizhi
 * @date 2018/11/14 16:01
 * @CacheConfig: 类级别的注解：如果我们在此注解中定义cacheNames，则此类中的所有方法上
 * @Cacheable的cacheNames默认都是此值。当然@Cacheable也可以重定义cacheNames的值
 */
@Service
@CacheConfig(cacheNames = "user")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @CacheEvict(key = "'user'+#user.id", beforeInvocation = false)
    public void delete(User user) {
        userMapper.deleteById(user.getId());
    }

    //condition和unless 只满足特定条件才进行缓存：
    //condition: 在执行方法前，condition的值为true，则缓存数据
    //unless ：在执行方法后，判断unless ，如果值为true，则不缓存数据
    //conditon和unless可以同时使用，则此时只缓存同时满足两者的记录

    /**
     * 条件缓存：
     * 只有满足condition的请求才可以进行缓存，如果不满足条件，则跟方法没有@Cacheable注解的方法一样
     * 如下面只有id < 3才进行缓存
     */
    @Override
    @CachePut(condition = "#result != 'null'", key = "'user'+#user.id")
    public User update(User user) {
        User checkResult = userMapper.selectById(user.getId());
        if (checkResult == null) {
            return null;
        }
        userMapper.updateById(user);
        System.out.println(user);
        return user;
    }

    /**
     *如果设置sync=true，
     *如果缓存中没有数据，多个线程同时访问这个方法，则只有一个方法会执行到方法，其它方法需要等待
     *如果缓存中已经有数据，则多个线程可以同时从缓存中获取数据
     */
    @Override
    @Cacheable(key = "'user'+#id", sync = true)
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    @CachePut(key = "'user'+#result.id")
    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }

}
