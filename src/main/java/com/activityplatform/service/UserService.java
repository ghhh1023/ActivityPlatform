package com.activityplatform.service;


import com.activityplatform.pojo.User;
import com.activityplatform.pojo.UserInfo;

public interface UserService {
    public Boolean login(String username, String password);

    public Boolean logout();

    public void register(User user);

    public boolean alterUserInfo(UserInfo userInfo);

    public UserInfo getUserInfo(Integer id);

    public User getUserByUserId(Integer id);

    public Boolean getCodeForReg(String username);

    public Boolean getCodeForLogin(String username);

    public User getUserByUserName(String userName);


}
