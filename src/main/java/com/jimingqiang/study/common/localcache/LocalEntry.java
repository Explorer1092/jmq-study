package com.jimingqiang.study.common.localcache;

/**
 * Created by QDHL on 2018/8/31.
 *
 * @author mingqiang ji
 */
public class LocalEntry<T> {

    private static long DEFAULT_EXPIRED_TIME = 86400000L;

    private long time = System.currentTimeMillis();

    private T obj;

    private long expired;


    public LocalEntry(T obj) {
        this.obj = obj;
    }

    public LocalEntry(T obj, long expired) {
        this.obj = obj;
        this.expired = expired;
    }

    public long getTime() {
        return time;
    }

    public T getObj() {
        return obj;
    }

    public long getExpired() {
        return expired;
    }

    public boolean isExpired(){

        Long accessTime = System.currentTimeMillis();
        return accessTime - this.getTime() > this.getExpired();

    }
}
