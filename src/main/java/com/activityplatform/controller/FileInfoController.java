package com.activityplatform.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.activityplatform.common.RetJson;
import com.activityplatform.exception.ActivityException;
import com.activityplatform.pojo.FileInfo;
import com.activityplatform.service.FileInfoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/file")
public class FileInfoController {

    private static final String BASE_PATH = System.getProperty("user.dir")+"/src/main/resources/static/file/";

    @Resource
    private FileInfoService FileInfoService;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public RetJson upload(@RequestParam("files") MultipartFile[] files) {
        List<FileInfo> uploadedFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalName = file.getOriginalFilename();
            if (originalName == null || originalName.isEmpty()) {
                return RetJson.fail(-1, "文件名不能为空");
            }
            String fileName = FileUtil.mainName(originalName) + System.currentTimeMillis() + "." + FileUtil.extName(originalName);
            try {
                FileUtil.writeBytes(file.getBytes(), BASE_PATH + fileName);

                FileInfo info = new FileInfo();
                info.setOriginName(originalName);
                info.setFileName(fileName);
                FileInfo addInfo = FileInfoService.add(info);

                if (addInfo != null) {
                    uploadedFiles.add(addInfo);
                } else {
                    return RetJson.fail(-3, "上传失败");
                }
            } catch (IOException e) {
                e.printStackTrace(); // 或者记录错误日志
                return RetJson.fail(-3, "上传失败");
            }
        }

        Map<String, List<FileInfo>> resMap = new HashMap<>();
        resMap.put("fileInfoList", uploadedFiles);
        return RetJson.success(resMap, "上传成功");
    }


    /**
     * update
     */
    @PutMapping("/update")
    public RetJson update(@RequestBody FileInfo FileInfo){
        FileInfoService.update(FileInfo);
        return RetJson.success(0,"修改成功");
    }

    /**
     * delete
     */
    @DeleteMapping("/delete/{id}")
    public RetJson delete(@PathVariable Long id){
        FileInfoService.delete(id);
        return RetJson.success(0,"删除成功");
    }

    /**
     * find
     */
    @GetMapping("/detail/{id}")
    public RetJson detail(@PathVariable Long id){
        return RetJson.success("fileInfo", FileInfoService.findById(id),"获取成功");
    }

    /**
     * 下载文件
     */
    @GetMapping("/download/{id}")
    public void download(@PathVariable String id, HttpServletResponse response) throws IOException {
        if(StrUtil.isBlank(id)||"null".equals(id)){
            throw new ActivityException("-1","您未上传文件");
        }
        FileInfo FileInfo = FileInfoService.findById(Long.parseLong(id));
        if(FileInfo==null){
            throw new ActivityException("-2","没找到该文件");
        }
        byte[] bytes = FileUtil.readBytes(BASE_PATH+FileInfo.getFileName());
        response.reset();
        response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(FileInfo.getOriginName(),"UTF-8"));
        response.addHeader("Content-Length",""+bytes.length);
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(bytes);
        toClient.flush();
        toClient.close();
    }
}
