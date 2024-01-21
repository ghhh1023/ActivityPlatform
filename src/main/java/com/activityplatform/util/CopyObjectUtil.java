package com.activityplatform.util;

import java.lang.reflect.Field;

public class CopyObjectUtil {
    public static void copyFieldValue(Object newObject, Object pastObject){
        for(Field f : newObject.getClass().getDeclaredFields()){
            f.setAccessible(true);
            try {
                if(f.get(newObject) == null&&f.get(pastObject) != null){
                    f.set(newObject,f.get(pastObject));
                }
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }
}
