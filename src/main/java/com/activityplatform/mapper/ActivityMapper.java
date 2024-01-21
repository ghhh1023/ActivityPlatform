package com.activityplatform.mapper;


import com.activityplatform.pojo.Activity;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ActivityMapper extends Mapper<Activity> {
    @Select("select a.id,a.name,a.img_ids,a.time,a.location,a.content,a.promoter_id,u.name as promoter_name, u.career as promoter_career,is_examine from activity a inner join user_info u on u.id = a.promoter_id")
    public List<Activity> getAllActivities();


}