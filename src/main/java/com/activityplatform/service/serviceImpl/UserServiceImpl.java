package com.activityplatform.service.serviceImpl;


import com.activityplatform.common.RetJson;
import com.activityplatform.config.Client;
import com.activityplatform.mapper.SubscribeMapper;
import com.activityplatform.mapper.UserInfoMapper;
import com.activityplatform.mapper.UserMapper;
import com.activityplatform.pojo.User;
import com.activityplatform.pojo.UserInfo;
import com.activityplatform.service.RedisService;
import com.activityplatform.service.UserService;
import com.activityplatform.util.CodeUtil;
import com.activityplatform.util.JwtUtil;
import com.activityplatform.util.MobileMessageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    final char []codeSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
    final int SALT_LENGTH = 8;//盐值长度
    final int ENCRYPT_NUM=1024;//加密次数

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SubscribeMapper subscribeMapper;


    @Override
    public Boolean getCodeForReg(String username) {
        String verificationCode = CodeUtil.generateVerificationCode(4);
        Client.Request request=null;
        Client client=new Client();
        request= MobileMessageUtil.sendIdentifyingCode(username, verificationCode, 5);
        //在redis中存入用户的账号和对应的验证码
        redisService.set(username,verificationCode,60 * 5);
        return true;
    }

    @Override
    public Boolean getCodeForLogin(String username) {
        String verificationCode = CodeUtil.generateVerificationCode(4);
        Client.Request request=null;
        Client client=new Client();
        request= MobileMessageUtil.sendIdentifyingCode(username, verificationCode, 7);
        //在redis中存入用户的账号和对应的验证码
        redisService.set("login" + username,verificationCode,60 * 5);
        return true;
    }

    @Override
    public Boolean login(User user) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            currentUser.login(token);//登入验证
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public Boolean logout(){
        Subject currentUser=SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()){
            currentUser.logout();
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void register(User user)
    {
        String password = user.getPassword();
        String salt=produceSalt();//生成八位的盐值
        ByteSource byteSource=ByteSource.Util.bytes(salt);
        SimpleHash simpleHash=new SimpleHash("md5",password,byteSource,ENCRYPT_NUM);
        user.setPassword(simpleHash.toHex());
        user.setSalt(salt);
        userMapper.register(user);
    }


    /**
     * 修改 姓名 性别 年龄 email
     * @param userInfo
     * @return
     */
    @Override
    public boolean alterUserInfo(UserInfo userInfo) {
        UserInfo d=userInfoMapper.getUserInfoById(userInfo.getId());
        if (userInfo.getName()==null){
            userInfo.setName(d.getName());
        }
        if (userInfo.getAge()==null){
            userInfo.setAge(d.getAge());
        }
        if (userInfo.getSex()==null){
            userInfo.setSex(d.getSex());
        }
        if (userInfo.getEmail()==null){
            userInfo.setEmail(d.getEmail());
        }
        if (userInfo.getCareer()==null){
            userInfo.setCareer(d.getCareer());
        }
        userInfoMapper.alterUserInfo(userInfo);
        return true;
    }

    @Override
    public UserInfo getUserInfo(Integer id) {
        UserInfo userInfo = userInfoMapper.getUserInfoById(id);
        return userInfo;
    }

    @Override
    public User getUserByUserId(Integer valueOf) {
        return userMapper.getUserByUserId(valueOf);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public List<Integer> getAllSubscription(Integer uId) {
        return subscribeMapper.getSubscriptionByUId(uId);
    }

    public String produceSalt()
    {
        StringBuilder randomString= new StringBuilder();
        Random random = new Random();
        for(int i = 0;i < SALT_LENGTH;i++)
        {
            String strRand = null;
            strRand = String.valueOf(codeSequence[random.nextInt(62)]);
            randomString.append(strRand);
        }
        return randomString.toString();
    }
}
