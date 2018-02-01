package com.spider.song.spiderquartz.QuartzCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: quartz job注入spring-bean为空的解决方案(功能类)
 * @User: songzhengjie
 * @Date: 2018-01-31
 * @Time: 18:51
 */

@Configuration
@EnableScheduling
public class QuartzSchedule {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private AutowireJobFactory autowireJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws Exception {

        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setOverwriteExistingJobs(true);
        //延时启动
        factoryBean.setStartupDelay(20);
        //加载quartz数据源配置
        //factoryBean.setQuartzProperties(quartzProperties());
        // 自定义Job Factory，【用于Spring注入】
        factoryBean.setJobFactory(autowireJobFactory);

        return factoryBean;
    }


    /**
     * 加载quartz数据源配置
     *
     * @return
     * @throws IOException
     */
    //@Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource(null));//"/quartz.properties"
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

}
