package com.activityplatform.pojo;

public class FileInfo {

    private Long id;

    private String originName;


    private String fileName;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getOriginName() {
        return originName;
    }


    public void setOriginName(String originName) {
        this.originName = originName == null ? null : originName.trim();
    }


    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }
}