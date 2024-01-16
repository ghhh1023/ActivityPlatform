package com.activityplatform.service;


import com.activityplatform.pojo.User;
import com.activityplatform.pojo.UserInfo;

public interface UserService {
    public Boolean login(String userName, String password);

    public Boolean logout();

    public void register(User user);

    public boolean alterUserInfo(UserInfo userInfo);

    public UserInfo getUserInfo(Integer id);

    User getUserByUserId(Integer id);


}
