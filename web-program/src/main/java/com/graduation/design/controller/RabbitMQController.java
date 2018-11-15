package com.graduation.design.controller;

import com.graduation.design.rabbit.CallBackSender;
import com.graduation.design.rabbit.FanoutSender;
import com.graduation.design.rabbit.HelloSender1;
import com.graduation.design.rabbit.TopicSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RabbitMQController
 * @Author XuWeiZhi
 * @Description
 * @Date 2018-11-15 22:25 星期四 web
 * @VERSION 1.0.0
 **/
@RestController
@RequestMapping("/rabbit")
@Slf4j
public class RabbitMQController {

    @Autowired
    private HelloSender1 helloSender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private CallBackSender callBackSender;

    @RequestMapping("/send")
    public String sendMessage() {
        helloSender.send();
        return "发送成功";
    }

    @RequestMapping("/sends")
    public String sendMessages() {
        for(int i=0;i<3;i++){
            helloSender.send("hellomsg:"+i);
        }
        return "发送成功ss";
    }

    @RequestMapping("/sendUser")
    public String sendUser() {
        helloSender.sendUser();
        return "发送成功la";
    }

    @RequestMapping("/topic")
    public String topic() {
        topicSender.send();
        return "发送成功lasss";
    }

    /**
     * fanout exchange类型rabbitmq测试
     */
    @GetMapping("/fanoutTest")
    public void fanoutTest() {
        System.out.println("xxx");
        fanoutSender.send();
    }

    @GetMapping("/callback")
    public void callbak() {
        callBackSender.send();
    }


    /*@RabbitListener(queues = "helloQueue")
    @RabbitHandler
    public void receiveMessage(String hello){
         log.info("This is my recivece message:{}",hello);
        System.out.println("Yeah!I have already received message !");
    }*/
}
