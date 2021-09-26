package com.qf.jedis.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Cjl
 * @date 2021/8/9 17:06
 */
public class JedisUtil {

    private static GenericObjectPoolConfig poolConfig;
    private static String host;
    private static JedisPool jedisPool;


    static {
        poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(10);
        poolConfig.setMinIdle(4);
        host = "192.168.140.129";
        jedisPool = new JedisPool(poolConfig,host);
    }

    public static Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
    public static JedisPool getJedisPool(){
        return jedisPool;
    }

}
