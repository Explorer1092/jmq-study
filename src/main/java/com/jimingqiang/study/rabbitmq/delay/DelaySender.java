package com.jimingqiang.study.rabbitmq.delay;

import com.jimingqiang.study.Bean.Order;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by QDHL on 2018/9/25.
 *
 * @author mingqiang ji
 */
@Component
public class DelaySender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void sendDelay(Order order) {
        System.out.println("【订单生成时间】" + new Date().toString() +"【1分钟后检查订单是否已经支付】" + order.toString() );

        this.amqpTemplate.convertAndSend(DelayConfig.ORDER_DELAY_EXCHANGE, DelayConfig.ORDER_DELAY_ROUTING_KEY, order, message -> {
            // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(1 * 1000 * 60 + "");

            return message;
        });






    }

}
