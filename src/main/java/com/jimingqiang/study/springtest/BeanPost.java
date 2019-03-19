package com.jimingqiang.study.springtest;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class BeanPost implements IBeanPost,InitializingBean {

    @Override
    public void testProxy(){
        System.out.println("代理测试******");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是初始化方法*********");
    }

    @PostConstruct
    public void initPost(){
        System.out.println("我是Post方法**********");
    }
}
