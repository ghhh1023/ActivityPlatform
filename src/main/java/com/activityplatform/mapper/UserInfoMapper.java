package com.activityplatform.mapper;



import com.activityplatform.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info where id=#{id}")
    public UserInfo getUserInfoById(@Param("id") Integer id);

    @Update({"update user_info set name=#{name},age=#{age},sex=#{sex},email=#{email},career=#{career} where id=#{id}"})
    public void alterUserInfo(UserInfo userInfo);


}
