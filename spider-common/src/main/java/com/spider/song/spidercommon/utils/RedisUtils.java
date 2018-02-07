package com.spider.song.spidercommon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:
 * User: songzhengjie
 * Date: 2018-02-07
 * Time: 17:27
 * ========================================
 */
public class RedisUtils {

    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class.getSimpleName());

    private RedisUtils() {
        //防止外部实例化
    }

    private static Jedis jedis;
    private static Lock lock = new ReentrantLock();


    /**
     * 获取redis连接实例，一定记得关闭连接
     *
     * @return
     */
    public static Jedis getJedis(){
        if (jedis == null) {
            lock.lock();
            try {
                if (jedis == null) {
                    String host = PropertiesUtils.getProperty("jedis.host");
                    int port = Integer.valueOf(PropertiesUtils.getProperty("jedis.port"));
                    JedisPool jedisPool = new JedisPool(host, port);
                    jedis = jedisPool.getResource();
                    jedis.auth(PropertiesUtils.getProperty("jedis.auth.password"));
                    logger.info("[getJedis]::jedis:连接实例+1");
                }
            } catch (Exception e) {
                logger.info("[getJedis]::取redis连接实例出错:", e);
            } finally {
                lock.unlock();
            }
        } else {
            logger.info("[getJedis]::jedis:重用连接池中实例");
        }

        return jedis;

    }

    /**
     * 关闭jedis的连接通道，释放资源
     *
     * @param jedis
     */
    public static void closeJedisConnection(Jedis jedis) {
        if (jedis.isConnected()) {
            jedis.close();
            logger.info("[closeJedisConnection]::jedis连接已关闭");
        }
    }

    /**
     * 获取redis缓存中的value
     * @param key
     * @return
     */
    public static String get(String key){
        jedis = getJedis();
        String value = jedis.get(key);
        closeJedisConnection(jedis);
        logger.info("[get]::key:{}缓存中获取到value:{}",key,value);
        return value;
    }

}
