package com.xiaomai.myproject.utils;

/**
 * Created by XiaoMai on 2016/11/30 17:19.
 */
public class ParseUtils {

    /**
     * 把Object转换为int型
     *
     * @param obj
     * @return
     */
    public static int parseToInteger(Object obj) {
        if (obj == null) {
            return 0;
        } else {
            try {
                return Integer.parseInt(obj.toString());
            } catch (Exception e) {
                return 0;
            }
        }

    }

    /**
     * 把Object转换喂long型
     *
     * @param obj
     * @return
     */
    public static long parseToLong(Object obj) {
        if (obj == null) {
            return 0;
        } else {
            try {
                return Long.parseLong(obj.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }
}
