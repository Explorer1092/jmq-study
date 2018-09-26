package com.jimingqiang.study.rabbitmq.direct;

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
public class HelloSender {

    @Autowired
    private AmqpTemplate template;

    public void send() {
        for (int i = 0 ; i< 10;i++){
            User user = new User();
            user.setName(i+"");
            user.setAge(i);
            template.convertAndSend("queue",user);

        }
    }

}
