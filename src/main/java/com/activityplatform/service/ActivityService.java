package com.activityplatform.service;

import com.activityplatform.pojo.Activity;
import com.activityplatform.pojo.ActivityDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityService {

    public List<Activity> getAllActivities();

    public Activity add(Activity activity);

    public Boolean isSubscribe(Integer uId, Integer aId);

    public Boolean delete(Integer id);

    public Boolean alter(Activity activity);

    public Activity getActivityById(Integer id);

    public ActivityDetail getActivityDetailByAId(Integer aId);

    public Boolean subscribe(Integer uId, Integer aId);

    public Boolean unsubscribe(Integer uId, Integer aId);
}
