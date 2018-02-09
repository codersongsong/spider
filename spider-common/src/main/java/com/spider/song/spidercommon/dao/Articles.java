package com.spider.song.spidercommon.dao;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:
 * User: songzhengjie
 * Date: 2018-02-09
 * Time: 17:30
 * ========================================
 */
public class Articles {

    private String Title;//图文消息标题
    private String Description;//图文消息描述
    private String PicUrl;//图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
    private String Url;//点击图文消息跳转链接

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}