package com.activityplatform.service;

import com.activityplatform.mapper.FileInfoMapper;
import com.activityplatform.pojo.FileInfo;

public interface FileInfoService {

    public FileInfo add(FileInfo FileInfo);


    public void update(FileInfo FileInfo);


    public void delete(Long id);

    public FileInfo findById(Long id);
}
