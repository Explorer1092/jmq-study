package com.jimingqiang.study;

import com.jimingqiang.study.component.ThreadPoolTest;
import com.jimingqiang.study.config.PropertyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolTests {

    @Autowired
    private ThreadPoolTest threadPoolTest;
    @Autowired
    private PropertyTest propertyTest;

    @Test
    public void test(){
        /*threadPoolTest.hello();
        threadPoolTest.hello1();*/;
        System.out.println(propertyTest.getName());
    }

}
