
package com.xiaomai.myproject;

import android.test.AndroidTestCase;

import com.xiaomai.myproject.utils.FileUtils;

/**
 * Created by XiaoMai on 2016/12/13 11:07.
 */
public class FileUtilsTest extends AndroidTestCase {

    public void testGetRootPath() {
        // assertEquals("", FileUtils.getRootPath());
        // assertEquals("", FileUtils.getCacheRootPath());
        // assertEquals("",
        // FileUtils.formateSize(FileUtils.getFolderSize("/storage")));
        assertEquals("100", FileUtils.copyFolders(
                "/storage/emulated/legacy/com.vyanke1", "/storage/emulated/legacy/com.vyanke"));
    }

}
