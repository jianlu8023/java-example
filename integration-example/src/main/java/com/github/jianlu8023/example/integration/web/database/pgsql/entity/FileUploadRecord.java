package com.github.jianlu8023.example.integration.web.database.pgsql.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.*;
import java.util.*;


@TableName(value = "file_upload_record")
public class FileUploadRecord implements Serializable {

    @TableId(value = "uid")
    private Integer uid;


    @TableField(value = "file_name")
    private String fileName;


    @TableField(value = "file_size")
    private Long fileSize;


    @TableField(value = "uploader_name")
    private String uploaderName;


    @TableField(value = "uploader_email")
    private String uploaderEmail;


    @TableField(value = "upload_time")
    private Date uploadTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    public Integer getUid() {
        return uid;
    }


    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }


    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }


    public String getUploaderName() {
        return uploaderName;
    }


    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }


    public String getUploaderEmail() {
        return uploaderEmail;
    }


    public void setUploaderEmail(String uploaderEmail) {
        this.uploaderEmail = uploaderEmail;
    }


    public Date getUploadTime() {
        return uploadTime;
    }


    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FileUploadRecord other = (FileUploadRecord) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
                && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
                && (this.getUploaderName() == null ? other.getUploaderName() == null : this.getUploaderName().equals(other.getUploaderName()))
                && (this.getUploaderEmail() == null ? other.getUploaderEmail() == null : this.getUploaderEmail().equals(other.getUploaderEmail()))
                && (this.getUploadTime() == null ? other.getUploadTime() == null : this.getUploadTime().equals(other.getUploadTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getUploaderName() == null) ? 0 : getUploaderName().hashCode());
        result = prime * result + ((getUploaderEmail() == null) ? 0 : getUploaderEmail().hashCode());
        result = prime * result + ((getUploadTime() == null) ? 0 : getUploadTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", uploaderName=").append(uploaderName);
        sb.append(", uploaderEmail=").append(uploaderEmail);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}