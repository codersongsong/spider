package com.spider.song.spiderquartz;

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


    public static void main(String[] args) {

        String host = "47.91.155.122";
        int port = 2015;
        JedisPool jedisPool = new JedisPool(host,port);

        Jedis jedis = jedisPool.getResource();
        jedis.auth("!QAZ2wsx!QAZ");
        System.out.println(jedis);

        //jedis.setex("name", 10000,"我爱吃火锅");
        String value = jedis.get("senderNickName");
        String name = jedis.get("name");
        System.out.println(name);
        long l = jedis.del("senderNickName");
        System.out.println(l);
        String ss = jedis.flushAll();
        System.out.println(ss);
    }

}
