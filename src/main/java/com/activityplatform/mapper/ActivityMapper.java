package com.activityplatform.mapper;


import com.activityplatform.pojo.Activity;

import com.activityplatform.pojo.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ActivityMapper extends Mapper<Activity>{
    @Select("select a.id,a.name,a.img_ids,a.time,a.location,a.content,a.promoter_id,u.name as promoter_name, u.career as promoter_career,is_examine from activity a inner join user_info u on u.id = a.promoter_id")
    public List<Activity> getAllActivities();


    @Delete("delete from activity where id=#{id}")
    public boolean deleteById(@Param("id")int id);


    @Select("select id,name,img_ids,time,location,content,promoter_id,is_examine from activity where id=#{id}")
    public Activity getActivityById(@Param("id") Integer id);

    @Update({"update activity set name=#{name},img_ids=#{imgIds},time=#{time},location=#{location},content=#{content},promoter_id=#{promoterId},is_examine=#{isExamine} where id=#{id}"})
    public void alterActivity(Activity activity);
}
