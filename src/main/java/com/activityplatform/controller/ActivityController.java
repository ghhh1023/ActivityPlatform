package com.activityplatform.controller;


import com.activityplatform.common.RetJson;
import com.activityplatform.pojo.Activity;
import com.activityplatform.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @RequestMapping("/showActivities")
    public RetJson alterUserInfo(){
        Map<String, List<Activity>> resMap = new HashMap<>();
        resMap.put("activities", activityService.getAllActivities());
        return RetJson.success(resMap, "获取成功");
    }
}
