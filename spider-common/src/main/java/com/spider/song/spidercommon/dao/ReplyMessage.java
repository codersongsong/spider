package com.spider.song.spidercommon.dao;

import java.util.List;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:
 * User: songzhengjie
 * Date: 2018-02-09
 * Time: 14:24
 * ========================================
 */
public class ReplyMessage {

    private String ToUserName;//接收方帐号（收到的OpenID）
    private String FromUserName;//开发者微信号
    private String CreateTime;//消息创建时间 （整型）
    private String MsgType;//text
    private String Content;//回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
    private String ArticleCount;//图文消息个数，限制为8条以内

    public List<Articles> getArticles() {
        return Articles;
    }

    public void setArticles(List<Articles> articles) {
        Articles = articles;
    }

    private List<Articles> Articles;//多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应



    public String getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(String articleCount) {
        ArticleCount = articleCount;
    }
    //
    //public String getArticles() {
    //    return Articles;
    //}
    //
    //public void setArticles(String articles) {
    //    Articles = articles;
    //}


    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }



}
