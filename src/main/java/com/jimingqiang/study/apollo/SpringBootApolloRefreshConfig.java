package com.jimingqiang.study.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringBootApolloRefreshConfig  {

    @ApolloConfig
    private Config config;

    @Autowired
    SampleRedisConfig sampleRedisConfig;

    @PostConstruct
    public void init(){
        System.out.println("--------------------------------------");
        System.out.println(sampleRedisConfig.getCommandTimeout());
        System.out.println(sampleRedisConfig.getExpireSeconds());
        System.out.println("--------------------------------------");

    }

    @ApolloConfigChangeListener
    private void someOnChange(ConfigChangeEvent changeEvent) {
        //update injected value of batch if it is changed in Apollo
        if (changeEvent.isChanged("test")) {
            System.out.println("test is change"+ config.getIntProperty("test",456));
        }
    }

}
