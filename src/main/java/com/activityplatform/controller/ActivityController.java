package com.activityplatform.controller;


import com.activityplatform.common.RetJson;
import com.activityplatform.pojo.Activity;
import com.activityplatform.pojo.ActivityDetail;
import com.activityplatform.pojo.User;
import com.activityplatform.pojo.UserInfo;
import com.activityplatform.service.ActivityService;
import com.activityplatform.util.CopyObjectUtil;
import com.activityplatform.util.ValidatedUtil;
import com.activityplatform.vo.ShowActivity;
import io.lettuce.core.dynamic.annotation.Param;
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
    @PutMapping("/update")
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


    /**
     * 获取活动详情
     * @param aId
     * @return
     */
    @RequestMapping("/getActivityDetail/{aId}")
    public RetJson getUserInfo(@PathVariable Integer aId){
        ActivityDetail activityDetail = activityService.getActivityDetailByAId(aId);
        if (activityDetail==null){
            return RetJson.fail(-1,"获取活动详情失败");
        }else{
            return RetJson.success("activityDetail",activityDetail,"获取成功");
        }
    }

    /**
     * 订阅
     * @param aId
     * @return
     */
    @RequestMapping("/subscribe/{aId}")
    public RetJson subscribe(@PathVariable Integer aId){
        Activity pastActivity = activityService.getActivityById(aId);
        Integer uId = user.getId();
        if (pastActivity == null){
            return RetJson.fail(-2,"活动不存在");
        }
        if (activityService.isSubscribe(uId, aId)){
            return RetJson.fail(-1,"已订阅，不可重复订阅");
        }
        activityService.subscribe(uId, aId);
        return RetJson.success(0,"订阅成功");
    }

    /**
     * 取消订阅
     * @param aId
     * @return
     */
    @RequestMapping("/unsubscribe/{aId}")
    public RetJson unsubscribe(@PathVariable Integer aId){
        Activity pastActivity = activityService.getActivityById(aId);
        Integer uId = user.getId();
        if (pastActivity == null){
            return RetJson.fail(-2,"活动不存在");
        }
        if (!activityService.isSubscribe(uId, aId)){
            return RetJson.fail(-1,"还未订阅");
        }
        activityService.unsubscribe(uId, aId);
        return RetJson.success(0,"取消订阅成功");
    }

}
