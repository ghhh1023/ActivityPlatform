package com.activityplatform.service.serviceImpl;

import cn.hutool.core.collection.CollectionUtil;
import com.activityplatform.mapper.ActivityMapper;
import com.activityplatform.mapper.SubscribeMapper;
import com.activityplatform.pojo.Activity;
import com.activityplatform.pojo.ActivityDetail;
import com.activityplatform.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return subscribeMapper.isSubscribe(uId, aId) > 0;
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

    @Override
    public ActivityDetail getActivityDetailByAId(Integer aId) {
        ActivityDetail activityDetail = activityMapper.getActivityDetailByAId(aId);
        activityDetail.setSubscribersCount(subscribeMapper.getSubscriptionCountByAId(aId));
        return activityDetail;
    }

    @Override
    @Transactional
    public Boolean subscribe(Integer uId, Integer aId) {
        try {
            ActivityDetail activityDetail = activityMapper.getActivityDetailByAId(aId);
            activityDetail.setSubscribersCount(activityDetail.getSubscribersCount() + 1);
            activityMapper.alterActivityDetail(activityDetail);

            int rowsAffected = subscribeMapper.subscribe(uId, aId);

            if (rowsAffected > 0) {
                // 如果上述操作都成功，提交事务
                return true;
            } else {
                // 如果插入操作没有成功，抛出异常，触发事务回滚
                throw new RuntimeException("Failed to subscribe. Rolling back transaction.");
            }
        } catch (Exception e) {
            // 处理异常，触发事务回滚
            throw new RuntimeException("Error while subscribing. Rolling back transaction.", e);
        }
    }

    @Override
    public Boolean unsubscribe(Integer uId, Integer aId) {
        try {
            ActivityDetail activityDetail = activityMapper.getActivityDetailByAId(aId);
            activityDetail.setSubscribersCount(activityDetail.getSubscribersCount() - 1);
            activityMapper.alterActivityDetail(activityDetail);

            int rowsAffected = subscribeMapper.unsubscribe(uId, aId);

            if (rowsAffected > 0) {
                // 如果上述操作都成功，提交事务
                return true;
            } else {
                // 如果插入操作没有成功，抛出异常，触发事务回滚
                throw new RuntimeException("Failed to subscribe. Rolling back transaction.");
            }
        } catch (Exception e) {
            // 处理异常，触发事务回滚
            throw new RuntimeException("Error while subscribing. Rolling back transaction.", e);
        }
    }
}
