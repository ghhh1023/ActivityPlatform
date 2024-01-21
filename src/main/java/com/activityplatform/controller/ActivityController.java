package com.activityplatform.controller;


import com.activityplatform.common.RetJson;
import com.activityplatform.pojo.Activity;
import com.activityplatform.pojo.User;
import com.activityplatform.pojo.UserInfo;
import com.activityplatform.service.ActivityService;
import com.activityplatform.util.CopyObjectUtil;
import com.activityplatform.util.ValidatedUtil;
import com.activityplatform.vo.ShowActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    User user=null;
    UserInfo userInfo=null;

    @ModelAttribute
    public void common(HttpServletRequest request){
        user=(User)request.getAttribute("user");
        userInfo=(UserInfo)request.getAttribute("userInfo");
    }

    @RequestMapping("/showActivities")
    public RetJson alterUserInfo(){
        Map<String, List<ShowActivity>> resMap = new HashMap<>();
        List<Activity> activities = activityService.getAllActivities();
        List<ShowActivity> showActivities = new ArrayList<>();
        Integer id = user.getId();
        for (Activity activity : activities){
            ShowActivity tmpAct = new ShowActivity(activity);
            tmpAct.setIsSubscribe(activityService.isSubscribe(id, activity.getId()));
            showActivities.add(tmpAct);
        }
        resMap.put("activities", showActivities);
        return RetJson.success(resMap, "获取成功");
    }


    /**
     * add
     */
    @PostMapping("/add")
    public RetJson add(@RequestBody Activity activity){
        activity.setPromoterId(user.getId());
        activityService.add(activity);
        return RetJson.success(0, "添加成功");
    }



    @DeleteMapping("/delete/{id}")
    public RetJson delete(@PathVariable int id, HttpServletRequest request){
        String isAdmin = request.getAttribute("isAdmin").toString();
        if ("false".equals(isAdmin)){
            return RetJson.fail(-9,"权限不足，请联系管理员");
        }
        activityService.delete(id);
        return RetJson.success(0, "删除成功");
    }


    /**
     * 修改活动信息
     * @param activity 用户信息，字段为：
     * @param request
     * @return
     */
    @RequestMapping("/update")
    public RetJson alterActivity(@RequestBody Activity activity, HttpServletRequest request){
        if (!ValidatedUtil.validate(activity)){
            return RetJson.fail(-1,"请检查参数");
        }
        String isAdmin = request.getAttribute("isAdmin").toString();
        if ("false".equals(isAdmin)){
            return RetJson.fail(-9,"权限不足，请联系管理员");
        }
        Activity pastActivity = activityService.getActivityById(activity.getId());
        if (pastActivity == null){
            return RetJson.fail(-2,"活动不存在");
        }
        CopyObjectUtil.copyFieldValue(activity,pastActivity);
        activityService.alter(activity);
        return RetJson.success(0,"修改成功");
    }


}
