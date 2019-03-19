package com.jimingqiang.study.mybatisgenerator.entity;

import java.util.Date;

public class friendCircleGiveCourse {
    private Integer id;

    private Long parentId;

    private Long studentId;

    private String serialNumber;

    private Date shareTime;

    private Byte channel;

    private Byte posterType;

    private Byte shareState;

    private Byte state;

    private Byte source;

    private Date giftTime;

    private Date createTime;

    private Date updateTime;

    private Date scanTime;

    private Long authorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public Byte getChannel() {
        return channel;
    }

    public void setChannel(Byte channel) {
        this.channel = channel;
    }

    public Byte getPosterType() {
        return posterType;
    }

    public void setPosterType(Byte posterType) {
        this.posterType = posterType;
    }

    public Byte getShareState() {
        return shareState;
    }

    public void setShareState(Byte shareState) {
        this.shareState = shareState;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public Date getGiftTime() {
        return giftTime;
    }

    public void setGiftTime(Date giftTime) {
        this.giftTime = giftTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getScanTime() {
        return scanTime;
    }

    public void setScanTime(Date scanTime) {
        this.scanTime = scanTime;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}