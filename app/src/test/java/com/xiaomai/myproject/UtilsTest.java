
package com.xiaomai.myproject;

import com.xiaomai.myproject.utils.FileUtils;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by XiaoMai on 2016/12/14 14:44.
 */
public class UtilsTest {

    @Test
    public void testGetMD5() throws Exception {
//        assertEquals("", Utils.getMD5("123"));
        assertEquals("",
                FileUtils.Unzip(
                        new File("/storage/emulated/legacy/com.vyanke/latexs/f3084c79ed7b668a67241f9c8a91141b_.zip"),
                        "/storage/emulated/legacy/1/"));
    }
}
