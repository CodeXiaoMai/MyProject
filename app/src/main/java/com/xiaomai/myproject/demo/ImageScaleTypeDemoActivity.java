
package com.xiaomai.myproject.demo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.ZoomControls;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;

/**
 * Created by XiaoMai on 2016/12/2 10:09.
 */
public class ImageScaleTypeDemoActivity extends BaseActivity {

    int[] images = new int[] {
            R.drawable.git_big_jb51
    };

    int currentImg = 0;

    private float alpha = 1;

    @Override
    protected void initViews() {
        Button plus = (Button) findViewById(R.id.plus);
        Button minus = (Button) findViewById(R.id.minus);
        Button next = (Button) findViewById(R.id.next);
        final ImageView img1 = (ImageView) findViewById(R.id.image1);
        final ImageView img2 = (ImageView) findViewById(R.id.image2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(images[++currentImg % images.length]);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alpha += 0.1;
                if (alpha >= 1) {
                    alpha = 1;
                }
                img1.setAlpha(alpha);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alpha -= 0.1;
                if (alpha <= 0) {
                    alpha = 0;
                }
                img1.setAlpha(alpha);
            }
        });

        img1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img1.getDrawable();
                // 获取第一个图片显示框中的位图
                Bitmap bitmap = bitmapDrawable.getBitmap();
                // bitmap图片实际大小与第一个ImageView的缩放比例
                double scale = bitmap.getHeight() / img1.getHeight() * 1.0;
                // 获取需要显示的图片的开始点
                double x = event.getX() * scale;
                double y = event.getY() * scale;
                if (x + 120 > bitmap.getWidth()) {
                    x = bitmap.getWidth() - 120;
                }
                if (y + 120 > bitmap.getHeight()) {
                    y = bitmap.getHeight() - 120;
                }
                // 显示图片的制定区域
                img2.setImageBitmap(Bitmap.createBitmap(bitmap, (int) x, (int) y, 120, 120));
                return true;
            }
        });

        ZoomControls zoomControls = (ZoomControls) findViewById(R.id.zoom);
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        QuickContactBadge quickContactBadge = (QuickContactBadge) findViewById(R.id.search_badge);
        quickContactBadge.assignContactFromPhone("123456", false);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.image_scale_type;
    }
}
