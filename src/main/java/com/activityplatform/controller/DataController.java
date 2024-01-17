package com.activityplatform.controller;


import com.activityplatform.common.RetJson;
import com.activityplatform.pojo.User;
import com.activityplatform.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/data")
public class DataController {


    User user=null;
    UserInfo userInfo=null;
    String isAdmin="false";

    @ModelAttribute
    public void common(HttpServletRequest request){
        user=(User)request.getAttribute("user");
        userInfo=(UserInfo)request.getAttribute("userInfo");
        isAdmin=request.getAttribute("isAdmin").toString();
    }


    @GetMapping("/test")
    public RetJson test(){
        Integer id= user.getId();
        if(id == null ){
            return RetJson.fail(-1,"用户不存在");
        }
        Map map=new LinkedHashMap();
        map.put("user", user);
        map.put("userInfo", userInfo);
        map.put("isAdmin", isAdmin);
        return RetJson.success(map, "0");
    }

}
