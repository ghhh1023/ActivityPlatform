package com.activityplatform.util;

public class CodeUtil {

    public static String generateVerificationCode(int n){
        StringBuilder stringBuilder=new StringBuilder();
        for(int i = 0;i < n;i++){
            int randomNum = (int)(Math.random() * 10);
            stringBuilder.append(randomNum);
        }
        return stringBuilder.toString();
    }
}
