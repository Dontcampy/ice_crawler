package com.dont39.ice_crawler.Dao.Redis.Filter;

public class BloomFilter implements Filter{
    @Override
    public boolean checkAdd(String str) {
        return false;
    }
}
