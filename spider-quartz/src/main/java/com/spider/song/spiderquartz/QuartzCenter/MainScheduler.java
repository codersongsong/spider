package com.spider.song.spiderquartz.QuartzCenter;

import com.spider.song.spiderquartz.task.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class MainScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    /**
     * MainScheduler.main()依次创建了scheduler（调度器）、job（任务）、trigger（触发器），其中，job指定了MyJob，trigger保存job的触发执行策略（每隔3s执行一次），scheduler将job和trigger绑定在一起，最后scheduler.start()启动调度，每隔3s触发执行JobImpl.execute()，
     * 打印出当前时间。
     * <p>
     * 除了SimpleScheduler之外，常用的还有CronTrigger.
     *
     * @return
     * @throws SchedulerException
     */


    //创建调度器
    public static Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();
        return factory.getScheduler();
    }


    public void scheduleJob() {
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();


        //除了SimpleScheduler之外，常用的还有CronTrigger.
        ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60*60).repeatForever();
        //创建触发器 每三分钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group2").withSchedule(scheduleBuilder).build();

        try {
            Scheduler scheduler = getScheduler();
            //将任务及触发器放入调度器中
            scheduler.scheduleJob(jobDetail, trigger);
            //调度器开始执行任务
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }

        } catch (SchedulerException e) {
            logger.error("任务调度异常:", e);
        }


    }


    public static void main(String[] args) {
        System.out.println("定时任务测试开始》》》");
        //scheduleJob();
        System.out.println("定时任务测试结束》》》");
    }

}
