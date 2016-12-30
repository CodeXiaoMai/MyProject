
package com.xiaomai.myproject;

import com.xiaomai.myproject.utils.Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by XiaoMai on 2016/12/14 14:44.
 */
public class UtilsTest {

    @Test
    public void testGetMD5() throws Exception {
        assertEquals("", Utils.getMD5("123"));
    }

    @Test
    public void testMatch() throws Exception {
        assertEquals("729316", Utils.match("【V研客】您的验证码是729316。不要把验证码泄露给其他人。"));
    }


}
