
package com.xiaomai.myproject.utils;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.xiaomai.myproject.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionViewBuilder {

    private Context mContext;

    private ImageLoader mImageLoader;

    private float mTextSize = 18;

    private int mLineSpacing = 0;

    public QuestionViewBuilder(Context context) {
        mContext = context;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(requestQueue, new BitmapCache());
    }

    public QuestionViewBuilder(Context context, float textSize) {
        this(context);
        mTextSize = textSize;
    }

    public void setDataToContainer(ViewGroup container, List<Object> list) {
        setDataToContainer(container, list, null);
    }

    public void setDataToContainer(ViewGroup container, List<Object> list, OnClickListener listener) {
        if (container == null || list == null || list.size() == 0) {
            return;
        }
        container.removeAllViews();
        final ArrayList<String> urls = new ArrayList<String>();
        for (Object obj : list) {
            if (obj instanceof TagHelper.PictureSpan) {
                //暂时先放百度的图片
                String url = "https://www.baidu.com/img/bd_logo1.png";
                urls.add(url);
            }
        }
        for (Object obj : list) {
            if (obj instanceof TagHelper.PictureSpan) {
                NetworkImageView imageView = new NetworkImageView(mContext);
                LayoutParams params = new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.setMargins(0,
                        mContext.getResources().getDimensionPixelSize(R.dimen.margin_large), 0,
                        mContext.getResources().getDimensionPixelSize(R.dimen.margin_large));
                imageView.setLayoutParams(params);
                imageView.setAdjustViewBounds(true);
                imageView.setDefaultImageResId(R.drawable.progress_circle_pic);
                imageView.setErrorImageResId(R.drawable.circle_normal);
                final String url = "https://www.baidu.com/img/bd_logo1.png";
                imageView.setImageUrl(url, mImageLoader);
                container.addView(imageView);
            } else {
                TextView textView = new TextView(mContext);
                textView.setLineSpacing(mLineSpacing, 1);
                textView.setText((SpannableStringBuilder) obj);
                textView.setTextSize(mTextSize);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                if (listener != null) {
                    textView.setOnClickListener(listener);
                }
                container.addView(textView);
            }
        }
    }
}
