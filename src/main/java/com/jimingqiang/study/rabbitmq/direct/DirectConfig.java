package com.jimingqiang.study.rabbitmq.direct;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by QDHL on 2018/9/25.
 *
 * @author mingqiang ji
 */
@Configuration
public class DirectConfig {

    @Bean
    public Queue queue() {
        return new Queue("queue");
    }
}
