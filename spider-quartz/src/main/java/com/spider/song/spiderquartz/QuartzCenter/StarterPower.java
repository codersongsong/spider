package com.spider.song.spiderquartz.QuartzCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: 触发定时器（跟随项目启动）
 * User: songzhengjie
 * Date: 2018-01-31
 * Time: 17:47
 */
@Component
@Order(value = 1)
public class StarterPower implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private MainScheduler mainScheduler;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("定时任务测试开始》》》");
        mainScheduler.scheduleJob();
        System.out.println("定时任务测试结束》》》");
    }
}
