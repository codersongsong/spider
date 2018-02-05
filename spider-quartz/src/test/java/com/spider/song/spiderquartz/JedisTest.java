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
        //Jedis jedis = jedisPool.getResource();

        Jedis jedis = new Jedis(host, port);
        System.out.println(jedis);

        //jedis.set("name", "songsong");
        String value = jedis.get("name");
        System.out.println(value);
    }

}
