package com.github.jianlu8023.multidbexample.web.db2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 文件
 * @TableName file
 */
@TableName(value ="file")
public class File implements Serializable {
    /**
     * 自增id
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /**
     * 文件名称
     */
    @TableField(value = "fileName")
    private String fileName;

    /**
     * 文件描述
     */
    @TableField(value = "fileDescribe")
    private String fileDescribe;

    /**
     * 文件大小
     */
    @TableField(value = "fileSize")
    private Long fileSize;

    /**
     * 文件上传时间
     */
    @TableField(value = "fileUploadTime")
    private String fileUploadTime;

    /**
     * 文件类型，文件后缀
     */
    @TableField(value = "fileType")
    private String fileType;

    /**
     * 是否被归档
     */
    @TableField(value = "isArchive")
    private Integer isArchive;

    /**
     * 是否被删除
     */
    @TableField(value = "isDelete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 自增id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 文件描述
     */
    public String getFileDescribe() {
        return fileDescribe;
    }

    /**
     * 文件描述
     */
    public void setFileDescribe(String fileDescribe) {
        this.fileDescribe = fileDescribe;
    }

    /**
     * 文件大小
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * 文件大小
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 文件上传时间
     */
    public String getFileUploadTime() {
        return fileUploadTime;
    }

    /**
     * 文件上传时间
     */
    public void setFileUploadTime(String fileUploadTime) {
        this.fileUploadTime = fileUploadTime;
    }

    /**
     * 文件类型，文件后缀
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 文件类型，文件后缀
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 是否被归档
     */
    public Integer getIsArchive() {
        return isArchive;
    }

    /**
     * 是否被归档
     */
    public void setIsArchive(Integer isArchive) {
        this.isArchive = isArchive;
    }

    /**
     * 是否被删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否被删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
        File other = (File) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getFileDescribe() == null ? other.getFileDescribe() == null : this.getFileDescribe().equals(other.getFileDescribe()))
            && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
            && (this.getFileUploadTime() == null ? other.getFileUploadTime() == null : this.getFileUploadTime().equals(other.getFileUploadTime()))
            && (this.getFileType() == null ? other.getFileType() == null : this.getFileType().equals(other.getFileType()))
            && (this.getIsArchive() == null ? other.getIsArchive() == null : this.getIsArchive().equals(other.getIsArchive()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFileDescribe() == null) ? 0 : getFileDescribe().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getFileUploadTime() == null) ? 0 : getFileUploadTime().hashCode());
        result = prime * result + ((getFileType() == null) ? 0 : getFileType().hashCode());
        result = prime * result + ((getIsArchive() == null) ? 0 : getIsArchive().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
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
        sb.append(", fileDescribe=").append(fileDescribe);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", fileUploadTime=").append(fileUploadTime);
        sb.append(", fileType=").append(fileType);
        sb.append(", isArchive=").append(isArchive);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}