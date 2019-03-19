package com.jimingqiang.study.springtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean("beanPost")
    public BeanPost initBeanPost(){
        return new BeanPost();
    }

}
