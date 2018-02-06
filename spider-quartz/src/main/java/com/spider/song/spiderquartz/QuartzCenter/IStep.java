package com.spider.song.spiderquartz.QuartzCenter;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:
 * User: songzhengjie
 * Date: 2018-02-06
 * Time: 14:17
 * ========================================
 */
public interface IStep<T> {

    void runTask(T t);


}
