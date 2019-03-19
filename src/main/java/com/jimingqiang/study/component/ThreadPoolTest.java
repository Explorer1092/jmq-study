package com.jimingqiang.study.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadPoolTest {

    @Async
    public void hello(){
        System.out.println(Thread.currentThread().getName()+"Hello World");
    }

    @Async
    public void hello1(){
        System.out.println(Thread.currentThread().getName()+"-Hello World1");
    }
}
