package com.activityplatform.service;

import com.activityplatform.pojo.Activity;

import java.util.List;

public interface ActivityService {

    public List<Activity> getAllActivities();

    public Activity add(Activity activity);

    public Boolean isSubscribe(Integer uId, Integer aId);
}
