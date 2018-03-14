package com.spider.song.spidercommon.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:
 * User: songzhengjie
 * Date: 2018-03-13
 * Time: 14:13
 * ========================================
 */
public class ThreadExample {

    public void ss() {
        try {
            JSONArray jsonArray = new JSONArray(100);
            List<Future<String>> futures = new ArrayList<>(jsonArray.size());
            CountDownLatch countDownLatch = new CountDownLatch(jsonArray.size());
            ExecutorService executorService = ThreadPoolUtils.getInstance();
            for (int i = 0; i < jsonArray.size(); i++) {
                int finalI = i;
                Callable<String> callable = new CallableImpl(countDownLatch, null, "logKey" + finalI);
                Future<String> future = executorService.submit(callable);
                futures.add(future);
            }
            countDownLatch.await(60, TimeUnit.SECONDS);
            for (int i = 0; i < futures.size(); i++) {
                String result = futures.get(i).get();
                System.out.println("result["+i+"]:"+result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
