package com.spider.song.spiderquartz.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MyJob implements Job {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private RenMinWebNoticeSpider renMinWebNoticeSpider;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("定时任务执行了execute方法");
        System.out.println("spider Starting……");
        String url = "http://econ.ruc.edu.cn/more_news.php?cid=10854";
        try {
            new RenMinWebNoticeSpider().runSpider(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("spider Ending……");
    }
}
