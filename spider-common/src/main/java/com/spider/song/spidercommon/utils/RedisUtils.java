package com.spider.song.spidercommon.utils;

import com.spider.song.spidercommon.encrypt.PropertySecurity;
import com.spider.song.spidercommon.statement.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import static com.spider.song.spidercommon.statement.Constants.JEDIS_FLAG.*;

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

    private static JedisPool jedisPool = null;

    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Constants.JEDIS_FLAG.MAX_ACTIVE);
            config.setMaxIdle(Constants.JEDIS_FLAG.MAX_IDLE);
            config.setMaxWaitMillis(Constants.JEDIS_FLAG.MAX_WAIT);
            config.setTestOnBorrow(Constants.JEDIS_FLAG.TEST_ON_BORROW);
            String host = PropertiesUtils.getProperty(JEDIS_HOST);
            int port = Integer.valueOf(PropertiesUtils.getProperty(JEDIS_PORT));
            String auth = PropertiesUtils.getProperty(JEDIS_AUTH_PASSWORD);
            jedisPool = new JedisPool(config, host, port, Constants.JEDIS_FLAG.TIMEOUT, auth);
            logger.info("[static initializer]::jedisPool初始化完成");
        } catch (Exception e) {
            logger.error("[static initializer]::jedisPool初始化出错:", e);
        }
    }


    /**
     * 获取redis连接实例，一定记得关闭连接
     *
     * @return
     */
    public static Jedis getJedisConnection() {
        Jedis jedis;
        if (jedisPool != null) {
            jedis = jedisPool.getResource();
            logger.info("[getJedisConnection]::jedis:连接实例+1,现有Active:{},Idle:{},NumWaiters:{}",jedisPool.getNumActive(),jedisPool.getNumIdle(),jedisPool.getNumWaiters());
            return jedis;
        } else {
            logger.info("[getJedisConnection]::未获取到连接实例,请检查异常");
            return null;
        }
    }

    /**
     * 关闭jedis的连接通道，释放资源
     *
     * @param jedis
     */
    public static void closeJedisConnection(Jedis jedis) {
        if (jedis!=null) {
            jedis.close();
            logger.info("[closeJedisConnection]::资源已归还,jedis连接已关闭!剩余Active:{},Idle:{},NumWaiters:{}",jedisPool.getNumActive(),jedisPool.getNumIdle(),jedisPool.getNumWaiters());
        }
    }

    /**
     * 获取redis缓存中的value
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = getJedisConnection();
        String value = jedis.get(key);
        closeJedisConnection(jedis);
        logger.info("[get]::key:{}缓存中获取到value:{}", key, value);
        return value;
    }

    /**
     * 获取放到redis缓存中的配置文件属性，内置补偿机制
     *
     * @param propName
     * @return
     * @throws Exception
     */
    public static String getProps(String propName) throws Exception {
        String propValue = RedisUtils.get(propName);
        if (propValue != null) {
            return PropertySecurity.convertProperty(propName, propValue);
        }

        return PropertiesUtils.getProperty(propName);
    }

}
