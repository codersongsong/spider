package com.spider.song.spidercommon.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * ========================================
 * Created with IntelliJ IDEA.
 * Description:基于redis的分布式锁实现
 * User: songzhengjie
 * Date: 2018-02-22
 * Time: 10:19
 * ========================================
 */
public class RedisLock {
    public static JedisPool jedisPool;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;


    /**
     *
     * 过期时间设置
     * EX second ：设置键的过期时间为 second 秒。 SET key value EX second 效果等同于 SETEX key second value 。
     * PX millisecond ：设置键的过期时间为 millisecond 毫秒。 SET key value PX millisecond 效果等同于 PSETEX key millisecond value 。
     *
     * 执行条件设置
     * NX ：只在键不存在时，才对键进行设置操作。 SET key value NX 效果等同于 SETNX key value 。
     * XX ：只在键已经存在时，才对键进行设置操作。
     */



    public static boolean tryLock(String key,String value,int expireSecond){
        Jedis jedis = RedisUtils.getJedisConnection();
        if(jedis == null){
            return false;
        }

        String result = jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireSecond);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    public static boolean releaseDistributedLock(String key,String value) {

        Jedis jedis = jedisPool.getResource();
        if(jedis == null){
            return false;
        }

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(value));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }


    public static void main(String[] args){
        //System.out.println(tryLock("A","B",100));
        //System.out.println(releaseDistributedLock("A","B"));
        int i = 0;
        System.out.println(++i);
    }
}