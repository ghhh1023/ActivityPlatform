package com.activityplatform.shiro;


import com.activityplatform.pojo.AdminUser;
import com.activityplatform.pojo.ExcludeURI;
import com.activityplatform.pojo.User;
import com.activityplatform.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Autowired
    AdminUser adminUser;

    //登入验证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)token;
        String username=usernamePasswordToken.getUsername();
        User dbuser=userService.getUserByUserName(username);

        if (dbuser==null){
            return null;//登入失败
        }
        String realmNam=getName();
        ByteSource salt = ByteSource.Util.bytes(dbuser.getSalt());
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(dbuser.getUsername(),dbuser.getPassword(),salt,realmNam);
        return info;
    }

    //权限添加逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object principal=principalCollection.getPrimaryPrincipal();
        Set<String> roles=new HashSet<>();
        roles.add("user");
        if (isAdmin(principal.toString())){
            roles.add("admin");
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        return info;
    }

    public boolean isAdmin(String username){
        List<String> list=adminUser.getAdminUser();
        if (list.contains(username)){
            return true;
        }
        return false;
    }
}
