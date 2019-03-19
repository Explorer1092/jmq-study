package com.jimingqiang.study.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestJavaConfigBean {

    @ApolloConfig
    private Config config;

    @Value("${test:200}")
    private int timeout;


    public int getTimeout() {
        return timeout;
    }

    @PostConstruct
    public void init(){
        System.out.println("+++++++++++++++");
        System.out.println(timeout);
        System.out.println("+++++++++++++++");

    }


    @ApolloConfigChangeListener
    private void someOnChange(ConfigChangeEvent changeEvent) {
        //update injected value of batch if it is changed in Apollo
        if (changeEvent.isChanged("test")) {
            this.timeout = config.getIntProperty("test",456);
            System.out.println("timeout is change:"+ timeout);
        }
    }




}
