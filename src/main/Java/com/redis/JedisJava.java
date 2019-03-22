package com.redis;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * 连接redis需要关闭redis服务的保护模式
 */
public class JedisJava {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.75.128",6379);
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator =  keys.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println(key);
        }
    }
}
