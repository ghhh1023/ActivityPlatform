package com.activityplatform.pojo;

//通过注入的方式获取不需要被拦截的URI

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "admin.list")
public class AdminUser {
    private List<String> adminUser=new LinkedList<>();

    public List<String> getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(List<String> adminUser) {
        this.adminUser = adminUser;
    }
}
