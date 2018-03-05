package com.spider.song.spidercommon;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:Effective Java 第一版推荐写法
 * User: songzhengjie
 * Date: 2018-02-27
 * Time: 19:05
 * ========================================
 */
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton (){}
    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}