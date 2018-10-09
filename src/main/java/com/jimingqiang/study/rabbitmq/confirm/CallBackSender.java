package com.jimingqiang.study.rabbitmq.confirm;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by QDHL on 2018/9/26.
 *
 * @author mingqiang ji
 */
@Component
public class CallBackSender implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public CallBackSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setConfirmCallback(this);
    }



    public void send(String topic, String message) {
        //rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("消息id:" + correlationData.getId());
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY, message, correlationData);

    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息id:" + correlationData.getId());
        if(ack){
            System.out.println("消息发送确认成功");

        }else{
            System.out.println("消息发送确认失败:" + cause);

        }

    }
}
