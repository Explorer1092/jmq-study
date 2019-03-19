package com.jimingqiang.study.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class PropertyTest {

    @Value("${name}")
    private String name;
}
