package com.dont39.ice_crawler.Dao.Redis.Filter;

import com.dont39.ice_crawler.Utils.MD5;
import com.dont39.ice_crawler.Utils.RedisTool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class DefaultFilter implements Filter{
    /**
     * 将url转换成md5后加入redis set
     * @param url 爬取到的url
     * @return 成功加入返回true
     */
    @Override
    public boolean checkAdd(String url) {
        JedisPool pool = RedisTool.getPool();
        Jedis jedis = pool.getResource();

        String fingerPrint = MD5.getMD5(url);
        Long result = jedis.sadd("filter", fingerPrint);

        jedis.close();
        return result != 0;
    }
}
