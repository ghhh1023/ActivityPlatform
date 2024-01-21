package com.activityplatform.service.serviceImpl;

import com.activityplatform.mapper.FileInfoMapper;
import com.activityplatform.pojo.FileInfo;
import com.activityplatform.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoMapper FileInfoMapper;
    /**
     * add
     */

    public FileInfo add(FileInfo FileInfo){
        FileInfoMapper.insertFile(FileInfo);
        return FileInfo;
    }

    /**
     * update
     */
    public void update(FileInfo FileInfo){
        FileInfoMapper.updateByPrimaryKeySelective(FileInfo);
    }

    /**
     * delete
     */
    public void delete(Long id){
        FileInfoMapper.deleteByPrimaryKey(id);
    }

    public FileInfo findById(Long id){
        return FileInfoMapper.getFileInfoById(id);
    }
}
