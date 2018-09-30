package com.dont39.ice_crawler.Dao.Redis;

import com.dont39.ice_crawler.FingerPrint.FingerPrint;
import com.dont39.ice_crawler.FingerPrint.FingerPrintFactory;
import com.dont39.ice_crawler.Utils.RedisTool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ResourceBundle;

public class UrlFilter {
    private FingerPrint fingerPrint;

    /**
     * 从setting.properties读取algorithm
     */
    public UrlFilter() {
        ResourceBundle bundle = ResourceBundle.getBundle("setting");
        String algorithm = bundle.getString("uf_algorithm");
        fingerPrint = FingerPrintFactory.getFactory().getFingerPrint(algorithm);
    }

    /**
     *
     * @param algorithm Normal or Bloom
     */
    public UrlFilter(String algorithm) {
        fingerPrint = FingerPrintFactory.getFactory().getFingerPrint(algorithm);
    }

    /**
     * 将url转换成finger print后加入redis set
     * @param url 爬取到的url
     * @return 成功加入返回true
     */
    public boolean add(String url) {
        if (fingerPrint != null) {
            JedisPool pool = RedisTool.getPool();
            Jedis jedis = pool.getResource();
            String fp = fingerPrint.convert(url);
            Long result = jedis.sadd("filter", fp);
            jedis.close();
            return result != 0;
        } else {
            return false;
        }
    }
}
