package com.activityplatform.intercepter;

import com.activityplatform.common.RedisSession;
import com.activityplatform.common.RetJson;
import com.activityplatform.pojo.ExcludeURI;
import com.activityplatform.service.RedisService;
import com.activityplatform.service.UserService;
import com.activityplatform.util.JwtUtil;
import com.auth0.jwt.interfaces.Claim;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * @author gh
 * 权限认证,如果失败,则返回授权失败信息
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    RedisService redisService;

    @Autowired
    ExcludeURI excludeURI;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        排除部分url
        String url=request.getRequestURI();
        if (isExclude(url)){
            return true;
        }

        //获取请求头部中的token
        String token=request.getHeader("Authorization");
        if (token!=null){

            //解密token
            Map<String, Claim> map= JwtUtil.VerifyToken(token);
            if (map==null){
                writeErrorInfo(response);
                return false;
            }
            String uuid=map.get("uuid").asString();
            String id=map.get("id").asString();
            String isAdmin = map.get("isAdmin").asString();
            //判断token是否有效
            if (uuid!=null&&id!=null&&redisService.exists("user:"+id)){
                System.out.println("user:"+userService.getUserByUserId(Integer.valueOf(id)));
                System.out.println("id:"+userService.getUserInfo(Integer.valueOf(id)));
                request.setAttribute("user",userService.getUserByUserId(Integer.valueOf(id)));
                request.setAttribute("userInfo",userService.getUserInfo(Integer.valueOf(id)));
                request.setAttribute("isAdmin",isAdmin);
                request.setAttribute("id",Integer.valueOf(id));
                String ret=(String) redisService.get("user:"+id);
                if (ret.equals(uuid)||true){
                    //更新过期时间,连续七天不活动则token失效
                    redisService.expire("user:"+id,60*60*24*7);

                    RedisSession redisSession= RedisSession.getInstance(uuid,Long.valueOf(id));
                    if (redisSession!=null){
                        request.setAttribute("redisSession",redisSession);
                    }

                    return true;
                }
            }
        }
        //否则提示token过期,要求重新登录
        writeErrorInfo(response);
        return false;
    }

    private void writeErrorInfo(HttpServletResponse response){
        try {
            Writer writer=response.getWriter();
            writer.write(RetJson.fail(-2,"Token has expired, please log in again").toString());
            writer.flush();
        }catch (Exception e){

        }
        return;
    }

    public boolean isExclude(String uri){
        List<String> list=excludeURI.getExcludeUri();
        if (list.contains(uri)){
            return true;
        }
        return false;
    }

}
