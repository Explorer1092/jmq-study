package com.jimingqiang.study.rabbitmq.delay;

import com.jimingqiang.study.Bean.Order;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by QDHL on 2018/9/25.
 *
 * @author mingqiang ji
 */
@Component
public class DelayRecevie {

    @RabbitListener(queues=DelayConfig.ORDER_QUEUE_NAME)
    public void processA(Order order, Message message, AMQP.Channel channel) {
        System.out.println("【orderDelayQueue 监听的消息】 - 【消费时间】 - [{"+new Date()+"}]- 【订单内容】 - [{"+ order.toString()+"}]");

        if (order.getOrderStatus() == 0) {
            order.setOrderStatus(2);
            System.out.println("【该订单未支付，取消订单】" + order.toString());
        } else if (order.getOrderStatus() == 1) {
            System.out.println("【该订单已完成支付】");
        } else if (order.getOrderStatus() == 2) {
            System.out.println("【该订单已取消】");
        }
    }

}
