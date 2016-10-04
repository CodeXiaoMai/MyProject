package com.xiaomai.myproject.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.xiaomai.myproject.R;

/**
 * 自定义ProgressDialog
 * Created by XiaoMai on 2016/9/6.
 */
public class MyProgressDialog extends Dialog {
    public MyProgressDialog(Context context) {
        super(context);
    }

    public MyProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public MyProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {

        private Context context;

        private boolean cancelable;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public MyProgressDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final MyProgressDialog dialog = new MyProgressDialog(context, R.style.MyDialogWithOutTitle);
            View view = inflater.inflate(R.layout.my_progressbar, null);
            dialog.setContentView(view);
            dialog.setCancelable(cancelable);
            return dialog;
        }
    }

}
