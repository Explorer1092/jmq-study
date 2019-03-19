package com.jimingqiang.study.zookeeper;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class DistributedLock {

    private ReentrantLock  lock = new ReentrantLock();
    private java.util.concurrent.locks.Condition condition = lock.newCondition();


    public static void main(String[] args) throws Exception {
      /* //创建zookeeper的客户端

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework client = CuratorFrameworkFactory.newClient("10.21.41.181:2181,10.21.42.47:2181,10.21.49.252:2181", retryPolicy);

        client.start();

        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");


        mutex.acquire();

        //获得了锁, 进行业务流程

        System.out.println("Enter mutex");

        //完成业务流程, 释放锁

        mutex.release();

        //关闭客户端

        client.close();*/

        Thread thread = Thread.currentThread();



        System.out.println("a");
        LockSupport.park();
        System.out.println("b");
        //LockSupport.unpark(thread);
       /* LockSupport.park();
        System.out.println("c");*/


    }

    static class Test {
        ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
        ThreadLocal<String> stringLocal = new ThreadLocal<String>();

        public void set() {
            longLocal.set(Thread.currentThread().getId());
            stringLocal.set(Thread.currentThread().getName());
        }

        public long getLong() {
            return longLocal.get();
        }

        public String getString() {
            return stringLocal.get();
        }

    }
}