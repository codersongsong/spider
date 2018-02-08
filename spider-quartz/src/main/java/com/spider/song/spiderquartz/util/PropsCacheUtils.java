package com.spider.song.spiderquartz.util;

import com.spider.song.spidercommon.utils.PropertiesUtils;
import com.spider.song.spidercommon.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Properties;
import java.util.Set;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:加载配置文件properties属性到redis缓存中
 * User: songzhengjie
 * Date: 2018-02-07
 * Time: 16:10
 * ========================================
 */
@Component
public class PropsCacheUtils implements CommandLineRunner{

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        thread.start();
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    refreshCache();
                    logger.info("[run]::配置文件props属性刷新redis缓存完成>>>>>>>>>");
                    Thread.currentThread().sleep(1000*60);//30分钟刷新一次redis缓存
                    //Thread.currentThread().sleep(1000*60*30);//30分钟刷新一次redis缓存
                } catch (Exception e) {
                    logger.error("[refreshCache]::配置文件props属性刷新缓存Redis出错:",e);
                }
            }
        }
    });

    public void refreshCache() throws Exception {
        logger.info("[refreshCache]::配置文件props属性刷新开始Start>>>>>>>>>");
        Resource resource = PropertiesUtils.getDefaultEnvResource();
        Properties properties = PropertiesUtils.makeProperties(resource);
        Set<String> propNames = properties.stringPropertyNames();
        Jedis jedis = RedisUtils.getJedisConnection();
        try {
            for (String name : propNames) {
                String value = properties.getProperty(name);
                jedis.setex(name, 60*60, value);
            }
        } catch (Exception e) {
            logger.error("[refreshCache]::刷新缓存Redis出错:",e);
            throw e;
        } finally {
            RedisUtils.closeJedisConnection(jedis);
        }
    }
}
