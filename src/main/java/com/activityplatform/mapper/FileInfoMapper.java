package com.activityplatform.mapper;

import com.activityplatform.pojo.FileInfo;
import com.activityplatform.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface FileInfoMapper extends Mapper<FileInfo> {

    @Insert("INSERT INTO file_info (origin_name, file_name) VALUES (#{originName}, #{fileName})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    Integer insertFile(FileInfo fileInfo);

    @Select("select id,origin_name,file_name from file_info where id=#{id}")
    public FileInfo getFileInfoById(@Param("id") Long id);

}