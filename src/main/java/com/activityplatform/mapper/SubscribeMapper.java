package com.activityplatform.mapper;

import com.activityplatform.pojo.Activity;
import com.activityplatform.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SubscribeMapper {
    @Select("SELECT COUNT(*) AS subscription_count\n" +
            "FROM subscribe\n" +
            "WHERE u_id = #{uId} AND a_id = #{aId}")
    public Integer getSubscriptionCount (@Param("uId") Integer uId,@Param("aId") Integer aId);
}
