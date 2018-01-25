package com.spider.song.spiderquartz.task;

import com.spider.song.spiderquartz.SpiderHome;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("定时任务执行了execute方法");
        System.out.println("spider Starting……");
        String url = "http://econ.ruc.edu.cn/more_news.php?cid=10854";
        try {
            SpiderHome.spiderRobot(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("spider Ending……");
    }
}
