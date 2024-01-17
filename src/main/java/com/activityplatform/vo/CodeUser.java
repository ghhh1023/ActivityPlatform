package com.activityplatform.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Validated
@Getter
@Setter
@AllArgsConstructor
public class CodeUser {
    @NotNull
//    @Length(max = 11, min = 11, message = "手机号的长度必须是11位.")
    @Pattern(regexp="^1[0-9]{10}$",message="手机号的长度必须是11位.")
    private String username;

    private String code;

    public CodeUser(){

    }

    @Override
    public String toString(){
        String ret=null;
        ObjectMapper mapper=new ObjectMapper();
        try {
            ret=mapper.writeValueAsString(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
}
