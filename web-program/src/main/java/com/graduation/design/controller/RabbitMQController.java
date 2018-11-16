package com.graduation.design.controller;

import com.graduation.design.rabbit.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Autowired
    private AnnotationRabbitMq annotationRabbitMq;

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

    @GetMapping("/anno")
    public void anno() {
        annotationRabbitMq.send();
    }

    @GetMapping("/order")
    public void order() {
        annotationRabbitMq.sendOrder();
    }

    @Scheduled(cron = "30 12 * * * *")
    public void datePrint(){
        annotationRabbitMq.sendOrder();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
    /*@RabbitListener(queues = "helloQueue")
    @RabbitHandler
    public void receiveMessage(String hello){
         log.info("This is my recivece message:{}",hello);
        System.out.println("Yeah!I have already received message !");
    }*/
}
