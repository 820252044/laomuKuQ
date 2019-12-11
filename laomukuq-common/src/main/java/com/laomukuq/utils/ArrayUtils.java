package com.laomukuq.utils;

/**
 * @author laomu
 * @version 1.0
 * @description 数组工具类
 * @date 2019-12-11
 */
public class ArrayUtils {

    public static String printArray(Object object[][]){
        StringBuilder sb = new StringBuilder();
        for (Object[] objects : object) {
            for (Object o : objects) {
                sb.append(o);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
