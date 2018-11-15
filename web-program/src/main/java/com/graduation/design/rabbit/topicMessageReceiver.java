package com.graduation.design.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class topicMessageReceiver {

    @RabbitListener(queues = "topic.message")
    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessageReceiver  : " +msg);
    }

    //由以上结果可知：sender1发送的消息,routing_key是“topic.message”，所以exchange里面的绑定的binding_key是“topic.message”，topic.
    // ＃都符合路由规则;所以sender1
    //发送的消息，两个队列都能接收到；
    //sender2发送的消息，routing_key是“topic.messages”，所以exchange里面的绑定的binding_key只有topic.＃都符合路由规则;所以sender2
    // 发送的消息只有队列
    //topic.messages能收到。
    @RabbitListener(queues = "topic.messages")
    @RabbitHandler
    public void processs(String msg) {
        System.out.println("topicMessagesReceiver  : " +msg);
    }

}