package com.lightblog.util;


import org.junit.Test;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 14:26 2017/10/21
 */
public class MD5UtilTest {

    @Test
    public void test() {
        String source = "123456";
        String md5Hex = MD5Util.md5(source);
        System.out.println(source + "‘s hex md5 -> " + md5Hex);

        String md5 = MD5Util.md5(source, "han");
        System.out.println(source + " salt -> " + " MD5 -> " + md5);

        source = "12345";

        md5 = MD5Util.md5(source, null);
        System.out.println(source + " salt -> " + " MD5 -> " + md5);

        source = "1234";
        md5 = MD5Util.md5(source, "");
        System.out.println(source + " salt -> " + " MD5 -> " + md5);
    }
}
