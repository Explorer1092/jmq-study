package com.jimingqiang.study.rabbitmq.direct;

import com.jimingqiang.study.Bean.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by QDHL on 2018/9/25.
 *
 * @author mingqiang ji
 */
@Component
public class HelloReceive {

    @RabbitListener(queues="queue")    //监听器监听指定的Queue
    public void processC(User str) {
        System.out.println("Receive:"+str);
    }
}
