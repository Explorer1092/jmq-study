package com.jimingqiang.study.common.localcache;

/**
 * Created by QDHL on 2018/8/31.
 *
 * @author mingqiang ji
 */
public class LocalCache<K, V> {

    private static final int DEFAULT_INIT_SIZE = 65536;
    private LocalCacheMap<K, LocalEntry<V>> valueMap;

    public LocalCache() {
        this.valueMap = new LocalCacheMap(65536);
    }

    public LocalCache(int initSize) {
        this.valueMap = new LocalCacheMap(initSize);
    }

    public V get(K key) {
        LocalEntry<V> localEntry = this.valueMap.get(key);
        if (localEntry == null) {
            return null;
        } else if (localEntry.isExpired()) {
            this.valueMap.remove(key);
            return null;
        } else {
            return localEntry.getObj();
        }
    }

    public void set(K key, V value) {
        this.valueMap.put(key, new LocalEntry(value));
    }

    public void set(K key, V value, long expired) {
        this.valueMap.put(key, new LocalEntry(value, expired));
    }

    public void remove(K key) {
        this.valueMap.remove(key);
    }

    public int size() {
        return this.valueMap.size();
    }


}
