package com.activityplatform.common;

import com.activityplatform.service.RedisService;
import com.activityplatform.util.JwtUtil;
import com.activityplatform.util.SpringUtil;
import com.auth0.jwt.interfaces.Claim;


import java.util.Map;

/**
 * @author gh
 * 通过token管理用户会话信息
 */
public class RedisSession {
    private static final boolean ONLINE=true;
    private static final boolean OFFLINE=false;

    private RedisService redisService= SpringUtil.getBean(RedisService.class);
    String sid=null;
    long id;

    private RedisSession(String uuid,long id){
        this.sid="session:"+uuid;
        this.id=id;
        if (!redisService.exists(uuid)){
            redisService.hSet(sid,"","");
            redisService.expire(sid,60*60*24*7);
        }
    }

    public static RedisSession getInstance(String uuid,long id){
        if (uuid==null||id<=0){
            return null;
        }
        return new RedisSession(uuid,id);
    }


    public static RedisSession getInstance(String token){
        Map<String, Claim> map= JwtUtil.VerifyToken(token);
        if (map==null){
            return null;
        }else{
            String uuid=map.get("uuid").asString();
            Long id=map.get("id").asLong();
            if (uuid==null||id==null){
                return null;
            }
            return new RedisSession(uuid,id);
        }
    }


    //设置参数
    public void setAttribute(String key,String value){
        redisService.hSet(sid,key,value);
    }
    //获取参数
    public String getAttribute(String key){
        return redisService.hGet(sid,key);
    }
    //删除参数
    public void removeAttribute(String key){
        redisService.hDel(sid,key);
    }

    //设置活动状态
    public void setUserActiveStatu(boolean flag){
        System.out.println("======================");
        redisService.setBit("curent_user",this.id,flag);
    }


}
