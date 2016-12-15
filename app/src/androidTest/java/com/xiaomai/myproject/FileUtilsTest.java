
package com.xiaomai.myproject;

import android.test.AndroidTestCase;

import com.xiaomai.myproject.utils.FileUtils;

import java.io.File;

/**
 * Created by XiaoMai on 2016/12/13 11:07.
 */
public class FileUtilsTest extends AndroidTestCase {

    public void testGetRootPath() {
        // assertEquals("", FileUtils.getRootPath());
        // assertEquals("", FileUtils.getCacheRootPath());
        // assertEquals("",
        // FileUtils.formateSize(FileUtils.getFolderSize("/storage")));
        /*
         * assertEquals("100", FileUtils.copyFolders(
         * "/storage/emulated/legacy/com.vyanke1",
         * "/storage/emulated/legacy/com.vyanke"));
         */

        assertEquals("", FileUtils.unZip(new File("/storage/emulated/legacy/Download/test1.apk"),
                "/storage/emulated/legacy/1/"));

        assertEquals("test1",
                FileUtils.getFileName(new File("/storage/emulated/legacy/Download/test1.apk")));
    }

}
