package com.activityplatform.controller;

import com.activityplatform.common.RetJson;
import com.activityplatform.pojo.User;
import com.activityplatform.service.RedisService;
import com.activityplatform.service.UserService;
import com.activityplatform.util.ValidatedUtil;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author gh
 * 与用户操作有关的控制器,如登入注册等
 */
@RestController
@Validated
public class UserController {
    private static final Integer MAX_SIZE=5*1024*1024;
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;


    /*
     获取手机验证码用于注册，如果手机号长度不合要求返回提示信息还没写
     */
    @RequestMapping("/getCodeForReg")
    public RetJson getCodeForReg( @Pattern(regexp="^1[0-9]{10}$",message="手机号的长度必须是11位.") @NotNull String phoneNumber){
        if(!ValidatedUtil.validate(phoneNumber)){
            return RetJson.fail(-2,"手机号码不合法");
        }
        if ((userService.getUserByUserName(phoneNumber)!=null)){
            return RetJson.fail(-1,"该用户已经注册");
        }
        userService.getCodeForReg(phoneNumber);
        return RetJson.success(0,"验证码已发送");
    }


    //获取手机验证码
    /*
     如果手机号长度不合要求返回提示信息还没写
     */
    @RequestMapping("/getCodeForLogin")
    public RetJson getCodeForLogin( @Pattern(regexp="^1[0-9]{10}$",message="手机号的长度必须是11位.") @NotNull String phoneNumber){
        if(!ValidatedUtil.validate(phoneNumber)){
            return RetJson.fail(-2,"手机号码不合法");
        }
        if ((userService.getUserByUserName(phoneNumber) == null)){
            return RetJson.fail(-1,"该用户还未注册");
        }
        userService.getCodeForLogin(phoneNumber);
        return RetJson.success(0,"验证码已发送");
    }

    /**
     * 注册
     //     * @param user
     //     * @param code
     * @return
     */
    @RequestMapping("/register")
    public RetJson userRegister(@Pattern(regexp="^1[0-9]{10}$",message="手机号的长度必须是11位.") String username,  @NotNull
    @Length(max = 16,min = 6,message = "密码不合法") String password , String code) {
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        if (!ValidatedUtil.validate(user)) {
            return RetJson.fail(-3, "请检查参数");
        }
        if (redisService.exists(user.getUsername()) && redisService.get(user.getUsername()).equals(code)) {
            if (userService.getUserByUserName(user.getUsername()) == null) {
                userService.register(user);
                return RetJson.success(0,"注册成功");
            }
            return RetJson.fail(-2, "用户已存在！");
        }
        return RetJson.fail(-1, "验证码不正确！");
    }
}
