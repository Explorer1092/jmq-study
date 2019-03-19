package com.jimingqiang.study.apollo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableApolloConfig
public class AppConfig {

    @Bean
    public SampleRedisConfig sampleRedisConfig(){
        return new SampleRedisConfig();
    }
}
