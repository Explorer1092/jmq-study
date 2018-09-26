package com.jimingqiang.study.rabbitmq.fanout;

import com.jimingqiang.study.Bean.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by QDHL on 2018/9/25.
 *
 * @author mingqiang ji
 */
@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate template;

    public void send() {
        for (int i = 0 ; i< 10;i++){
            //方法的第一个参数是交换机名称,第二个参数是发送的key,第三个参数是内容,RabbitMQ将会根据第二个参数去寻找有没有匹配此规则的队列
            template.convertAndSend("fanoutExchange","topic.message111","Hello,RabbitMq"+i);
        }
    }

}
