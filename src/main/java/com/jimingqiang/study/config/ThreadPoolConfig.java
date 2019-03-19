package com.jimingqiang.study.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    public static final int  corePoolSize = 5;
    public static final int  maxPoolSize = 10;
    public static final int  queueCapacity = 20;
    public static final int  threadTimeout = 60;

    @Bean(name = "callerRunsPolicy")
    public ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy(){
        return new ThreadPoolExecutor.CallerRunsPolicy();
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(@Qualifier("callerRunsPolicy") ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy) {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        // 设置队列容量
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        // 设置线程活跃时间（秒）
        threadPoolTaskExecutor.setKeepAliveSeconds(threadTimeout);
        threadPoolTaskExecutor.setRejectedExecutionHandler(callerRunsPolicy);
        threadPoolTaskExecutor.afterPropertiesSet();
        return threadPoolTaskExecutor;
    }


}
