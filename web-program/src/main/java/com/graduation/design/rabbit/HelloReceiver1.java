package com.graduation.design.rabbit;

import com.graduation.design.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloReceiver1 {

    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }

    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void process2(String hello) {
        System.out.println("Receiver2  : " + hello);
    }

    @RabbitListener(queues = "user")
    @RabbitHandler
    public void process2(User user) {
        System.out.println("Receiver2  : " + user.toString());
    }
}