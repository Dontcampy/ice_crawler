package com.dont39.ice_crawler.Dao.Redis;

import com.dont39.ice_crawler.Utils.RedisTool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class UrlQueue {
    private JedisPool pool;

    public UrlQueue() {
        pool = RedisTool.getPool();
    }

    /**
     * 将url加入list尾部
     * @param url 爬取到的url
     */
    public void push(String url) {
        Jedis jedis = pool.getResource();
        jedis.rpush("queue", url);
        jedis.close();
    }

    /**
     * 将list的头部数据pop
     * @return 爬取到的url
     */
    public String pop() {
        Jedis jedis = pool.getResource();
        String url = jedis.lpop("queue");
        jedis.close();
        return url;
    }
}
