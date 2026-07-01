package com.work.crudoperation.utils.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LocalCache {
    private final Map<String, CacheDate<String>> cache = new ConcurrentHashMap<>();

    public void put(String key, String value, Long expireTime) {
        Long expire = System.currentTimeMillis() + expireTime;
        cache.put(key, new CacheDate<>(value, expire));
    }

    public String get(String key) {
        CacheDate<String> data = cache.get(key);
        if (data == null) {
            return null;
        }
        if (data.isExpired()) {
            cache.remove(key);
            return null;
        }
        return data.getValue();

    }
}
