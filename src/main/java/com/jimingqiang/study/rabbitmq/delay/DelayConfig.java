package com.jimingqiang.study.rabbitmq.delay;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QDHL on 2018/9/25.
 *
 * @author mingqiang ji
 */
@Configuration
public class DelayConfig {

    /**
     *  延迟队列 TTL 名称
     */
    private static final String ORDER_DELAY_QUEUE = "user.order.delay.queue";

    /**
     * DLX，dead letter发送到的 exchange
     * 延时消息就是发送到该交换机
     *
     */
    public static final String ORDER_DELAY_EXCHANGE = "user.order.delay.exchange";

    /**
     * routing key 名称
     * 具体消息发送在该 routingKey 的
     */
    public static final String ORDER_DELAY_ROUTING_KEY = "order_delay";

    public static final String ORDER_QUEUE_NAME = "user.order.queue";

    public static final String ORDER_EXCHANGE_NAME = "user.order.exchange";

    public static final String ORDER_ROUTING_KEY = "order";


    /**
     * 延迟队列配置
     * 1、params.put("x-message-ttl", 5 * 1000);
     * 第一种方式是直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活,（当然二者是兼容的,默认是时间小的优先）
     *
     *  2、rabbitTemplate.convertAndSend(book, message -> {
     *  message.getMessageProperties().setExpiration(2 * 1000 + "");
     *   return message;
     *   });
     *
     *   第二种就是每次发送消息动态设置延迟时间,这样我们可以灵活控制
     *
     * @return
     */
    @Bean
    public Queue delayOrderQueue(){
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", ORDER_EXCHANGE_NAME);

        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", ORDER_ROUTING_KEY);

        return new Queue(ORDER_DELAY_QUEUE, true, false, false, params);

    }

    @Bean
    public DirectExchange orderDelayExchange() {
        return new DirectExchange(ORDER_DELAY_EXCHANGE);
    }


    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayOrderQueue()).to(orderDelayExchange()).with(ORDER_DELAY_ROUTING_KEY);
    }



    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange orderTopicExchange() {
        return new TopicExchange(ORDER_EXCHANGE_NAME);

    }

    @Bean
    public Binding orderBinding() {
        // TODO 如果要让延迟队列之间有关联,这里的 routingKey 和 绑定的交换机很关键
        return BindingBuilder.bind(orderQueue()).to(orderTopicExchange()).with(ORDER_ROUTING_KEY);

    }








}
