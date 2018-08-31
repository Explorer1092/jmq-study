package com.jimingqiang.study.common.localcache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by QDHL on 2018/8/31.
 *
 * @author mingqiang ji
 */
public class LocalCacheMap<K,V extends LocalEntry> extends LinkedHashMap<K,V> {

    private static final long serialVersionUID = 7443911984781135521L;
    private final int maxCapacity;
    private static final float LOAD_FACTOR = 0.99F;
    private static final float EXPIRED_CHECK_FACTOR = 0.6F;
    private static final float EXPIRED_DELETE_FACTOR = 0.25F;
    private static volatile boolean handle_expired = false;
    private static final ExecutorService pool = Executors.newSingleThreadScheduledExecutor();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public LocalCacheMap(int maxCapacity) {
        super(maxCapacity, 0.99F, false);
        this.maxCapacity = maxCapacity;
    }

    @Override
    public V get(Object key) {
        LocalEntry var2;
        try {
            this.lock.readLock().lock();
            var2 = (LocalEntry)super.get(key);
        } finally {
            this.lock.readLock().unlock();
        }

        return (V) var2;
    }

    @Override
    public V put(K key, V value) {
        LocalEntry var3;
        try {
            this.lock.writeLock().lock();
            var3 = (LocalEntry)super.put(key, value);
        } finally {
            this.lock.writeLock().unlock();
        }

        return (V) var3;
    }

    @Override
    public V remove(Object key) {
        LocalEntry var2;
        try {
            this.lock.writeLock().lock();
            var2 = (LocalEntry)super.remove(key);
        } finally {
            this.lock.writeLock().unlock();
        }

        return (V) var2;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        boolean isSaturated = this.size() > this.maxCapacity;
        if (isSaturated && !handle_expired) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    LocalCacheMap.this.checkExpired();
                }
            });
        }

        return isSaturated;
    }

    protected void checkExpired() {
        handle_expired = true;
        int checkNum = (new Float((float)this.size() * 0.6F)).intValue();
        int deleteNum = (new Float((float)this.size() * 0.25F)).intValue();
        LinkedList<Object> keyList = new LinkedList();

        for(Iterator iterator = this.entrySet().iterator(); iterator.hasNext() && (checkNum > 0 || deleteNum > 0); --checkNum) {
            Map.Entry entry = (Map.Entry)iterator.next();
            LocalEntry val = (LocalEntry)entry.getValue();
            if (val.isExpired()) {
                keyList.offer(entry.getKey());
                --deleteNum;
            }
        }

        Iterator var7 = keyList.iterator();

        while(var7.hasNext()) {
            Object key = var7.next();
            this.remove(key);
        }

        handle_expired = false;
    }


}
