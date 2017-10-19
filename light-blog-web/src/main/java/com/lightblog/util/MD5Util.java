package com.lightblog.util;

import org.springframework.util.DigestUtils;

/**
 * Encryption util with MD5.
 * @Author: Minsghan
 * @Date: Created in 23:50 2017/10/19
 */
public class MD5Util {
    /**
     * 使用MD5作信息摘要，并以十六进制表示
     */
    public static String md5(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }

    /**
     * 使用MD5作信息摘要，并以十六进制表示
     */
    public static String md5(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        return MD5Util.md5(s.getBytes());
    }

    public static void main(String[] args) {
        String source = "123456";
        String md5Hex = MD5Util.md5(source);
        System.out.println(source + "‘s hex md5 -> " + md5Hex);

        String saltSource = Salter.salt(source);
        String md5 = MD5Util.md5(saltSource);
        System.out.println(source + " salt -> " + saltSource + " MD5 -> " + md5);

        source = "12345";
        saltSource = Salter.salt(source);
        md5 = MD5Util.md5(saltSource);
        System.out.println(source + " salt -> " + saltSource + " MD5 -> " + md5);

        source = "1234";
        saltSource = Salter.salt(source);
        md5 = MD5Util.md5(saltSource);
        System.out.println(source + " salt -> " + saltSource + " MD5 -> " + md5);
    }
}
