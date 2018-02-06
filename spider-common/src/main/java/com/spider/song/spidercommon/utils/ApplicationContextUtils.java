package com.spider.song.spidercommon.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:获取spring的bean容器工具类
 * User: songzhengjie
 * Date: 2018-02-06
 * Time: 13:38
 * ========================================
 */
public class ApplicationContextUtils {

    private static WebApplicationContext applicationContext;

    private ApplicationContextUtils() {
        //防止外部实例化该类
    }

    /**
     * warn:在server启动时。Spring容器初始化时，不能通过下面方法获取Spring 容器
     */
    public static ApplicationContext getApplicationContext() {

        if (applicationContext == null) {
            synchronized (ApplicationContextUtils.class) {
                if (applicationContext == null) {
                    applicationContext = ContextLoader.getCurrentWebApplicationContext();
                }
            }
        }

        return applicationContext;
    }

    /**
     * 包装ApplicationContext中的方法getBean,获取容器中的bean
     */
    public static Object getBean(String name) throws Exception{
        return ApplicationContextUtils.getApplicationContext().getBean(name);
    }

    public static <T>T getBean(String name,Class<T> requiredType) throws Exception{
        return ApplicationContextUtils.getApplicationContext().getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) throws Exception {
        return ApplicationContextUtils.getApplicationContext().getBean(requiredType);
    }

}
