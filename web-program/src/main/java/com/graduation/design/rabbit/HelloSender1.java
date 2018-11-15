package com.graduation.design.rabbit;

import com.graduation.design.model.User;
import com.graduation.design.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author XuWeiZhi
 * @Date 2018-11-15 22:23 星期四 web
 * @VERSION 1.0.0
 **/
@Component
public class HelloSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private UserService userService;

    //单生产者和单消费者
    public void send() {
        String sendMsg = "hello1 " + new Date();
        System.out.println("Sender1 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("hello", sendMsg);
    }

    //单生产者-多消费者

    public void send(String msg) {
        String sendMsg = msg + new Date();
        System.out.println("Sender1 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("hello", sendMsg);
    }

    public void sendUser() {
        User user = userService.findById(55);
        this.rabbitTemplate.convertAndSend("user", user);
    }
}
