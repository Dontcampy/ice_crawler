package com.dont39.ice_crawler.Utils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class RedisTool {
    private static JedisPool pool;
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("setting");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.valueOf(bundle.getString("redis_maxTotal")));
        config.setMaxIdle(Integer.valueOf(bundle.getString("redis_maxIdle")));
        config.setMaxWaitMillis(Integer.valueOf(bundle.getString("redis_maxWait")));
        config.setTestOnBorrow(true);

        String host = bundle.getString("redis_host");
        Integer port = Integer.valueOf(bundle.getString("redis_port"));
        Integer timeout = Integer.valueOf(bundle.getString("redis_timeout"));
        String auth = bundle.getString("redis_auth");
        pool = new JedisPool(config, host, port, timeout, auth);
    }

    public static JedisPool getPool() {
        return pool;
    }

}
