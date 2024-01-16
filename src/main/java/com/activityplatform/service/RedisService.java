package com.activityplatform.service;

import java.util.Set;

public interface RedisService {

    public void set(String key, String value);


    public void set(String key, String value, long expireTime);

    public void expire(String key, long time);


    public void hSet(String hash, String key, String value);

    public String hGet(String hash, String key);

    public void hDel(String hash, String key);


    public boolean exists(String key) ;

    public Boolean remove(String key);


    public Object get(String key) ;

    public void sAdd(String key, String... arr);

    public void setBit(String key, long pos, boolean flag);

    public Set<String> sGet(String key);
}
