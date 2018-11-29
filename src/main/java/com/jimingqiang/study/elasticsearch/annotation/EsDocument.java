package com.jimingqiang.study.elasticsearch.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Auther: libo
 * @Date: 2018/8/2 11:01
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface EsDocument {
    String indexName();

    String indexType();
}
