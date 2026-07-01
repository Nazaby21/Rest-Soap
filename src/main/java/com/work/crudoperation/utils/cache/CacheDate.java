package com.work.crudoperation.utils.cache;

public class CacheDate<T> {
    private T value;
    private Long expireTime;

    public CacheDate(T value, Long expireTime) {
        this.value = value;
        this.expireTime = expireTime;
    }

    public T getValue() {
        return value;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expireTime;
    }
}
