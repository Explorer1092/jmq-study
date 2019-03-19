package com.jimingqiang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EnableAspectJAutoProxy
public class StudyApplication {



    public static void main(String[] args) {


       ApplicationContext run = SpringApplication.run(StudyApplication.class, args);


        //AopBean beanPost = (AopBean) run.getBean("aopBean");
       // beanPost.say("hello");
       /* User user = new User();
        user.setName("jmq");
        user.setAge(12);

        RedisUtil redisUtil = (RedisUtil) run.getBean("redisUtil");
        redisUtil.set("jmq",user);
        System.out.println("redis*****"+redisUtil.get("jmq").toString());

        final List<String> keys = Lists.newArrayList("tt","yy");
        RedisTemplate bean = (RedisTemplate) run.getBean("redisTemplate");*/

       /* bean.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                for (String key : keys) {
                    System.out.println(redisConnection.set(key.getBytes(),key.getBytes()));
                }
                return null;             }
        });

        List list = bean.executePipelined(new RedisCallback<Object>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for (String key : keys) {
                    redisConnection.get(key.getBytes());                }
                return null;
            }
        });*/

    }
}
