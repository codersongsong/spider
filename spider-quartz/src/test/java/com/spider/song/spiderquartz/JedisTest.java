package com.spider.song.spiderquartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @User: songzhengjie
 * @Date: 2018-02-05
 * @Time: 15:10
 */
public class JedisTest {

    private static Logger logger = LoggerFactory.getLogger(JedisTest.class.getSimpleName());

    public static void main(String[] args) {

        String host = "47.91.155.122";
        int port = 2015;
        JedisPool jedisPool = new JedisPool(host,port);

        Jedis jedis = jedisPool.getResource();
        jedis.auth("!QAZ2wsx!QAZ");
        System.out.println(jedis);

        //jedis.setex("name", 10000,"我爱吃火锅");
        String value = jedis.get("toEmailAccount");
        String name = jedis.get("sssss");
        System.out.println(value);
        String jedisRes = jedis.setex("sssss", 2 * 5, "666");
        System.out.println("jedisRes:"+jedisRes);
        //long l = jedis.del("senderNickName");
        //System.out.println(l);
        String ss = jedis.flushAll();
        System.out.println(ss);
        logger.info("[getJedisConnection]::jedis:连接实例+1,现有Active:{},Idle:{},NumWaiters:{}",jedisPool.getNumActive(),jedisPool.getNumIdle(),jedisPool.getNumWaiters());

    }

}
