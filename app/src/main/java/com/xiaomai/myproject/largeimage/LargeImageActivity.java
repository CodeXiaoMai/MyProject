
package com.xiaomai.myproject.largeimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.largeimage.widget.LargeImageView;

import java.io.IOException;
import java.io.InputStream;

public class LargeImageActivity extends BaseActivity {

    private LargeImageView iv_large_image;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_large_image;
    }

    @Override
    protected void initViews() {
        super.initViews();
        iv_large_image = (LargeImageView) findViewById(R.id.iv_large_image);
    }

    @Override
    protected void loadData() {
        super.loadData();
        try {
            InputStream inputStream = getAssets().open("world.jpg");
            // 获得图片的宽、高
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, tmpOptions);
            int width = tmpOptions.outWidth;
            int height = tmpOptions.outHeight;

            // 设置显示图片的中心区域
            //  [ˈridʒən] 区域
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            // preferred [prɪ'fɜd] 首选的
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(width / 2 - 200, height / 2 - 200,width / 2 + 200, height / 2 + 200), options);
//            iv_large_image.setImageBitmap(bitmap);
            iv_large_image.setInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
