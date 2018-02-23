package com.spider.song.spiderquartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.expression.Dates;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 *
 * Description:
 * User: songzhengjie
 * Date: 2018-02-06
 * Time: 10:33
 * ========================================
 */
@Component
public class SchuduledTaskTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private RenMinWebNoticeSpider renMinWebNoticeSpider;

    @Value("${instituteOfEconomicsURL}")
    private String ioeSpiderURL;

    @Scheduled(cron = "5 * * * * *")
    public void printString(){
        logger.debug("printString::url = [{}]",ioeSpiderURL);
        String url = ioeSpiderURL;
        logger.debug("定时任务springboot测试》》》》》》》》》"+url);
        if (renMinWebNoticeSpider != null) {
            logger.debug("[printString]::springboot定时任务可用:renMinWebNoticeSpider自动注入成功:{}",renMinWebNoticeSpider);
        }
    }

    public final static long ONE_Minute =  60 * 1000;//单位是毫秒

    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob(){
        logger.debug((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))+" >>fixedDelay(一分钟)执行....");
    }

    @Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob(){
        logger.debug((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))+" >>fixedRate(一分钟)执行....");
    }

    @Scheduled(cron="0 15 3 * * ?")
    public void cronJob(){
        logger.debug((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))+" >>cron执行....");
    }


}
