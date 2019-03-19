package com.jimingqiang.study.springtest.aop;

import org.springframework.stereotype.Component;

@Component
public class AopBean {
    public void say(String a){
        System.out.println("say*****"+a);
    }
}
