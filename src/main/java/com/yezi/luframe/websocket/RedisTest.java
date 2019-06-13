package com.yezi.luframe.websocket;

import redis.clients.jedis.Jedis;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2019/4/11 10:54
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("120.79.157.231",6379);
        jedis.append("key1","value1");
        System.out.println(jedis.get("key1"));
    }
}
