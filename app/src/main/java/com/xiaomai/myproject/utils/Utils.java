
package com.xiaomai.myproject.utils;

import android.text.Html;
import android.text.Spanned;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * 获取MD5加密的值
     * 
     * @param val
     * @return
     */
    public static String getMD5(String val) {
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(val.getBytes());
            byte[] bytes = md5.digest();

            StringBuilder hex = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString().toUpperCase();
        } catch (Exception e) {
            return val;
        }
    }

    /**
     * 匹配验证码
     * 
     * @param body
     * @return
     */
    public static String match(String body) {
        Pattern pattern = Pattern.compile("验证码是[A-Za-z0-9]{4,}(?![A-Za-z0-9])");
        Matcher m = pattern.matcher(body);
        if (m.find()) {
            return m.group().replace("验证码是","");
        }
        return "";
    }
}
