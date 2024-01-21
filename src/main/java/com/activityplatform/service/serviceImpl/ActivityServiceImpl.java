package com.activityplatform.service.serviceImpl;

import cn.hutool.core.collection.CollectionUtil;
import com.activityplatform.mapper.ActivityMapper;
import com.activityplatform.mapper.SubscribeMapper;
import com.activityplatform.pojo.Activity;
import com.activityplatform.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    SubscribeMapper subscribeMapper;

    public List<Activity> getAllActivities(){
        return activityMapper.getAllActivities();
    }

    /**
     * add
     */

    public Activity add(Activity activity){
        convertFileListToFields(activity);
        activityMapper.insertSelective(activity);
        return activity;
    }

    public Activity getActivityById(Integer id){
        return activityMapper.getActivityById(id);
    }

    @Override
    public Boolean isSubscribe(Integer uId, Integer aId) {
        return subscribeMapper.getSubscriptionCount(uId, aId) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return activityMapper.deleteById(id);
    }

    @Override
    public Boolean alter(Activity activity) {
        convertFileListToFields(activity);
        activityMapper.alterActivity(activity);
        return true;
    }

    /**
     * 页面传来的上传文件列表转换成以逗号隔开的id列表
     * @param activity
     */
    private void convertFileListToFields(Activity activity){
        List<Long> fileList = activity.getImgList();
        if(!CollectionUtil.isEmpty(fileList)){
            activity.setImgIds(fileList.toString());
        }
    }
}
