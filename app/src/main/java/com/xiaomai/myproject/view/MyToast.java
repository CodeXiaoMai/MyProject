package com.xiaomai.myproject.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaomai.myproject.R;

/**
 * 自定义的Toast
 * Created by XiaoMai on 2016/9/5.
 */
public class MyToast {

    public static Toast myToast;

    public static TextView tvContent;

    public static void show(Context context, int resId) {
        showToast(context, context.getResources().getString(resId), Toast.LENGTH_SHORT, false);
    }

    public static void show(Context context, String content) {
        showToast(context, content, Toast.LENGTH_SHORT, false);
    }

    public static void show(Context context, int resId, boolean newToast) {
        showToast(context, context.getResources().getString(resId), Toast.LENGTH_SHORT, newToast);
    }

    public static void show(Context context, String content, boolean newToast) {
        showToast(context, content, Toast.LENGTH_SHORT, newToast);
    }

    public static void showLongToast(Context context, int resId) {
        showToast(context, context.getResources().getString(resId), Toast.LENGTH_LONG, false);
    }

    public static void showLongToast(Context context, String content) {
        showToast(context, content, Toast.LENGTH_LONG, false);
    }

    public static void showToast(Context context, String content, int duration, boolean newToast) {
        if (context != null && !TextUtils.isEmpty(content)) {
            if (myToast == null || newToast) {
                myToast = new Toast(context);
                myToast.setDuration(duration);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.my_toast, null);
                tvContent = (TextView) view.findViewById(R.id.tv_content);
                myToast.setView(view);
            }
            tvContent.setText(content);
            myToast.show();
        }
    }
}
