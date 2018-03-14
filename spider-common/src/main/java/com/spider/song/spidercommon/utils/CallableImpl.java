package com.spider.song.spidercommon.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:
 * User: songzhengjie
 * Date: 2018-03-13
 * Time: 18:02
 * ========================================
 */
public class CallableImpl implements Callable<String> {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private CountDownLatch countDownLatch;
    private JSONObject jsonObject;
    private String logKey;

    public CallableImpl(CountDownLatch countDownLatch, JSONObject jsonObject, String logKey) {
        this.countDownLatch = countDownLatch;
        this.jsonObject = jsonObject;
        this.logKey = logKey;
    }

    @Override
    public String call() throws Exception {
        try {
            System.out.println("jsonObject:"+jsonObject.toJSONString());
            System.out.println("logKey:"+logKey);
            String result = jsonObject.toJSONString() + logKey;
            return result;
        } catch (Exception e) {
            logger.error("[call]::获取资源出错logKey:{}",logKey);
        } finally {
            countDownLatch.countDown();
        }

        return "404";
    }
}
