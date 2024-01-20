package com.activityplatform;

import com.activityplatform.util.SpringUtil;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.activityplatform.mapper")
public class ActivityPlatformApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ActivityPlatformApplication.class, args);
        SpringUtil.setApplicationContext(app);
    }

}
