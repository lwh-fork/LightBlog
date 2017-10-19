package com.lightblog.util;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 0:05 2017/10/20
 */
public class Salter {
    public static final String PREFIX = "HOW";
    public static final String FILLING = "ARE";
    public static final Integer FILLING_INDEX = 5;
    public static final String POSTFIX = "YOU!";

    public static String salt(String source) {
        if (source == null || source.length() == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(source);
        if (sb.length() > FILLING_INDEX) {
            sb.insert(FILLING_INDEX, FILLING);
        }

        return sb.insert(0, PREFIX).append(POSTFIX).toString();
    }
}
