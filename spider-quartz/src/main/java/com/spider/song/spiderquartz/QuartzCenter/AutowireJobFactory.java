package com.spider.song.spiderquartz.QuartzCenter;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 为了在JOB中使用Spring管理的Bean，需要重新定义一个Job Factory
 * @User: songzhengjie
 * @Date: 2018-01-31
 * @Time: 18:36
 */
@Deprecated
@Component
public class AutowireJobFactory extends AdaptableJobFactory {


    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;


    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入
        autowireCapableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }

}
