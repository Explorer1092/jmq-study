package com.jimingqiang.study;

import com.jimingqiang.study.rabbitmq.direct.HelloSender;
import com.jimingqiang.study.rabbitmq.fanout.FanoutSender;
import com.jimingqiang.study.rabbitmq.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by QDHL on 2018/9/25.
 *
 * @author mingqiang ji
 */
@SpringBootTest(classes=StudyApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;



    @Test
    public void testRabbit() {
        helloSender.send();
    }


    @Test
    public void testTopitRabbit() {
        topicSender.send();
    }

    @Test
    public void testFanoutRabbit() {
        fanoutSender.send();
    }
}
