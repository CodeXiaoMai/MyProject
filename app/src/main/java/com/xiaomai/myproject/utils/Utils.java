
package com.xiaomai.myproject.utils;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;

/**
 * Created by XiaoMai on 2016/9/2.
 */
public class Utils {

    /**
     * 将TextView的关键词设置为特殊的颜色
     *
     * @param src
     * @param keyWords
     * @param color
     * @return
     */
    public static Spanned setKeyWordsHighLight(String src, String keyWords, String color) {
        /**
         * 截取出src中keyWords的字符串用Html标签请你包裹， 再和keyWords之前和之后的字符串进行拼接
         */
        int index = src.indexOf(keyWords);
        int length = keyWords.length();
        src = src.substring(0, index) // keyWords之前的
                + "<font color=" + color + ">" + src.substring(index, index + length) + "</font>"
                + src.substring(index + length, src.length());// keyWords之后的
        return Html.fromHtml(src);
    }

    /**
     * Dip转换为像素
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        /**
         * 获取屏幕的像素密度
         */
        final float scale = context.getResources().getDisplayMetrics().density;
        if (dpValue > 0) {
            return (int) (dpValue * scale + 0.5f);
        } else {
            return (int) (dpValue * scale - 0.5f);
        }
    }


}
