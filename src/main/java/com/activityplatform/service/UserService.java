package com.activityplatform.service;


import com.activityplatform.common.RetJson;
import com.activityplatform.pojo.User;
import com.activityplatform.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    public Boolean login(User user);

    public Boolean logout();

    public void register(User user);

    public boolean alterUserInfo(UserInfo userInfo);

    public UserInfo getUserInfo(Integer id);

    public User getUserByUserId(Integer id);

    public Boolean getCodeForReg(String username);

    public Boolean getCodeForLogin(String username);

    public User getUserByUserName(String userName);

    public List<Integer> getAllSubscription(Integer uId);


}
