package com.jimingqiang.study.canal;

import com.jimingqiang.study.canal.utils.FilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wanglei on 25/03/2017.
 */
@Component
public class SpringInitAfterRun implements InitializingBean {
    private static final Logger _LOG = LoggerFactory.getLogger(SpringInitAfterRun.class);

    @Value("${sub.system.name}")
    private String subSystemName;
    @Autowired
    private DataPull DataPull;
    /*@Autowired
    private MailSendUtils mailSendUtils;*/

    @Override
    public void afterPropertiesSet() throws Exception {
        FilterUtils.init();
        Executors.newSingleThreadExecutor(new ThreadFactory() {
            private AtomicInteger atomicInteger = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "DataRecv-"+subSystemName+"-" + atomicInteger.get());
                thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        _LOG.error("****canal connect 线程出现问题 ****** {}", e);
                        //mailSendUtils.sendEmail("DataRecv-"+subSystemName+"-uncaughtException", e + "");
                    }
                });
                return thread;
            }
        }).execute(new Runnable() {
            @Override
            public void run() {
                DataPull.start();
            }
        });
    }
}
