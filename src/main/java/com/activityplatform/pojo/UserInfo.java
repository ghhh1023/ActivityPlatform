package com.activityplatform.pojo;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private  Integer id;

    private Integer uid;

    @Length(max = 11,min = 11,message = "手机号长度必须是11位")
    private String phoneNum;

    private String name;

    private int age;

    private int sex;

    @Email
    private String email;
}
