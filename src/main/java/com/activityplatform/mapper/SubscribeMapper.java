package com.activityplatform.mapper;

import com.activityplatform.pojo.Activity;
import com.activityplatform.pojo.User;
import com.activityplatform.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SubscribeMapper {
    @Select("SELECT COUNT(*) AS subscription_count\n" +
            "FROM subscribe\n" +
            "WHERE u_id = #{uId} AND a_id = #{aId}")
    public Integer isSubscribe (@Param("uId") Integer uId,@Param("aId") Integer aId);

    @Select("SELECT COUNT(*) AS subscription_count\n" +
            "FROM subscribe\n" +
            "WHERE a_id = #{aId}")
    public Integer getSubscriptionCountByAId (@Param("aId") Integer aId);

    @Insert({"insert into subscribe(u_id,a_id) values(#{uId},#{aId})"})
    @Options(useGeneratedKeys = true,keyColumn = "id")
    public int subscribe(@Param("uId") Integer uId,@Param("aId") Integer aId);

    @Delete("DELETE FROM subscribe WHERE u_id = #{uId} AND a_id = #{aId}")
    public int unsubscribe(@Param("uId") Integer uId, @Param("aId") Integer aId);

    @Select("SELECT a_id \n" +
            "FROM subscribe\n" +
            "WHERE u_id = #{uId}")
    public List<Integer> getSubscriptionByUId (@Param("uId") Integer uId);

}
