package com.activityplatform.service.serviceImpl;


import com.activityplatform.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
public class RedisServiceImpl implements RedisService {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void set(String key, String value, long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public void expire(String key,long time) {
        redisTemplate.expire(key,time,TimeUnit.SECONDS);
    }

    @Override
    public void hSet(String hash, String key, String value) {
        redisTemplate.opsForHash().put(hash,key,value);
    }

    @Override
    public String hGet(String hash, String key) {
        if (key==null){
            return null;
        }
        Object o=redisTemplate.opsForHash().get(hash,key);
        if (o==null){
            return null;
        }else{
            return (String)o;
        }
    }

    @Override
    public void hDel(String hash, String key) {
        redisTemplate.opsForHash().delete(hash,key);
    }


    @Override
    public void sAdd(String key, String... values){
//        redisTemplate.opsForSet().add(key,values,100);
    }

    @Override
    public Boolean remove(String key){
        return redisTemplate.delete(key);
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }


    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setBit(String key,long pos,boolean flag){
        redisTemplate.opsForValue().setBit(key,pos,flag);
    }

    @Override
    public Set<String> sGet(String key) {
        Set<String> set=redisTemplate.opsForSet().members(key);
        return set;
    }
}
