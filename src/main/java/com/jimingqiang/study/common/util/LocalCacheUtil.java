package com.jimingqiang.study.common.util;

import com.jimingqiang.study.common.localcache.LocalCache;

import java.util.concurrent.TimeUnit;

/**
 * Created by QDHL on 2018/8/31.
 *
 * @author mingqiang ji
 */
public class LocalCacheUtil {

    private static LocalCache DEFAULT__LOCAL_CACHE = new LocalCache();

    public LocalCacheUtil() {
    }

    public static Object get(String key) {
        return DEFAULT__LOCAL_CACHE.get(key);
    }

    public static void set(String key, Object value) {
        DEFAULT__LOCAL_CACHE.set(key, value);
    }

    public static void set(String key, Object value, long expired) {
        set(key, value, expired, TimeUnit.MILLISECONDS);
    }

    public static void set(String key, Object value, long expired, TimeUnit timeUnit) {
        DEFAULT__LOCAL_CACHE.set(key, value, timeUnit.toMillis(expired));
    }

    public static void remove(String key) {
        DEFAULT__LOCAL_CACHE.remove(key);
    }

}
