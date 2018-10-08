package com.dont39.ice_crawler.FingerPrint;

import com.dont39.ice_crawler.Utils.MD5;

/**
 * 使用MD5作为URL唯一标识
 */
public class Normal implements FingerPrint {
    @Override
    public String convert(String data) {
        return data == null ? null : MD5.getMD5(data);
    }
}
