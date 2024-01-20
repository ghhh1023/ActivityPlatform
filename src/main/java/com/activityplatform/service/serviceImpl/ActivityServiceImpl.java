package com.activityplatform.service.serviceImpl;

import com.activityplatform.mapper.ActivityMapper;
import com.activityplatform.pojo.Activity;
import com.activityplatform.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    public List<Activity> getAllActivities(){
        return activityMapper.getAllActivities();
    }
}
