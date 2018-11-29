package com.jimingqiang.study.elasticsearch.annotation;

import com.jimingqiang.study.elasticsearch.enums.EsFieldIndexEnum;
import com.jimingqiang.study.elasticsearch.enums.EsFieldTypeEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Auther: libo
 * @Date: 2018/8/2 11:01
 * @Description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface EsField {
    String name();

    EsFieldTypeEnum type();

    EsFieldIndexEnum index() default EsFieldIndexEnum.NO;

    String analyzer() default "";
}
