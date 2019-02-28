package com.itkang.blog.model;

import java.util.Date;

/**
 * Created by mis on 2018/12/18.
 */
public class BlogModel {

    private Integer id;
    private String title;
    private String introduction;
    private String keyword;
    private String images;
    private String content;
    private Integer clickNum;
    private Integer commentNum;
    private Integer positiveNum;
    private Integer isTop;
    private Integer isRecommend;
    private Date updateTime;
    private Date addTime;
    private String addTimeStr;
    private Short status;
    private String statusStr;
    private Integer typeId;
    private String typeStr;
    private Integer userId;
    private String blogName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getPositiveNum() {
        return positiveNum;
    }

    public void setPositiveNum(Integer positiveNum) {
        this.positiveNum = positiveNum;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getStatusStr() {
        if(this.status == 2){
            statusStr = "回收站";
        }else if(this.status == 1){
            statusStr = "已发布";
        }else{
            statusStr = "草稿";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getAddTimeStr() {
        return addTimeStr;
    }

    public void setAddTimeStr(String addTimeStr) {
        this.addTimeStr = addTimeStr;
    }
}
