package com.dont39.ice_crawler.Dao.Redis.Filter;

public class FilterFactory {
    private static final FilterFactory factory = new FilterFactory();

    private FilterFactory() {}

    /**
     * 使用单例模式获取Factory实例
     * @return factory instance
     */
    public static FilterFactory getFactory() {
        return factory;
    }

    public Filter getFilter(String filter) {
        if (filter == null) {
            return new DefaultFilter();
        } else if (filter.equals("DefaultFilter")) {
            return new DefaultFilter();
        } else if (filter.equals("BloomFilter")) {
            return new BloomFilter();
        }
        return new DefaultFilter();
    }
}
