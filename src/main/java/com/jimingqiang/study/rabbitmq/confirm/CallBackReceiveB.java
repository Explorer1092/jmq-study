package com.jimingqiang.study.rabbitmq.confirm;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by QDHL on 2018/9/26.
 *
 * @author mingqiang ji
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
public class CallBackReceiveB {
    @RabbitHandler
    public void process(String content) {

        System.out.println("接收处理队列B当中的消息： " + content);

    }

}
