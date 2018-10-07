package com.dont39.ice_crawler.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String getMD5(String str) {
        String md5 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            md5 = new BigInteger(1, md.digest()).toString(16); // 转成16进制字符串

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return md5 == null ? null : fillMD5(md5);
    }

    /**
     * 生成的md5可能会不够32位长度，需要使用0进行补位操作。
     * @param md5 生成的md5
     * @return 32位的md5
     */
    private static String fillMD5(String md5) {
        return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }
}
