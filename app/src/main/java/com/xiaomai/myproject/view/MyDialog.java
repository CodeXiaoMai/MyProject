package com.xiaomai.myproject.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaomai.myproject.R;

/**
 * Created by XiaoMai on 2016/9/1.
 */
public class MyDialog extends Dialog {

    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private OnClickListener onPositiveButtonClickListener;
        private OnClickListener onNegativeButtonClickListener;
        private boolean cancelable;

        /**
         * 默认情况下，两个按钮都可以用，分别为“取消”和“确定”
         *
         * @param context
         */
        public Builder(Context context) {
            this.context = context;
            this.positiveButtonText = context.getResources().getString(R.string.button_ok);
            this.negativeButtonText = context.getResources().getString(R.string.button_cancel);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(int resId) {
            this.title = context.getResources().getString(resId);
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int resId) {
            this.message = context.getResources().getString(resId);
            return this;
        }

        public Builder setPositvieButtonText(String positiveButtonText) {
            this.positiveButtonText = positiveButtonText;
            return this;
        }

        public Builder setPositvieButtonText(int resId) {
            this.positiveButtonText = context.getResources().getString(resId);
            return this;
        }

        public Builder setOnPositiveButtonClickListener(OnClickListener onPositiveButtonClickListener) {
            this.onPositiveButtonClickListener = onPositiveButtonClickListener;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder setNegativeButtonText(int resId) {
            this.negativeButtonText = context.getResources().getString(resId);
            return this;
        }

        public Builder setOnNeagtiveButtonClickListener(OnClickListener onNegativeButtonClickListener) {
            this.onNegativeButtonClickListener = onNegativeButtonClickListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public MyDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final MyDialog dialog = new MyDialog(context, R.style.MyDialogWithOutTitle);
            View view = inflater.inflate(R.layout.mydialog, null);
            //标题
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            //内容
            TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
            //取消按键
            Button btNegative = (Button) view.findViewById(R.id.bt_negative);
            //确定按钮
            Button btPositive = (Button) view.findViewById(R.id.bt_positive);
            //分隔线
            View divider = view.findViewById(R.id.divider);
            //如果标题为空，就隐藏标题
            if (TextUtils.isEmpty(title)) {
                tvTitle.setVisibility(View.GONE);
            } else {
                tvTitle.setText(title);
            }
            //设置内容
            if (!TextUtils.isEmpty(message)) {
                tvMessage.setText(message);
            }
            //设置取消按钮
            if (!TextUtils.isEmpty(negativeButtonText)) {
                btNegative.setText(negativeButtonText);
                if (onNegativeButtonClickListener != null) {
                    btNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onNegativeButtonClickListener.onClick(dialog, -1);
                        }
                    });
                }
            } else {
                btNegative.setVisibility(View.GONE);
                divider.setVisibility(View.GONE);
            }
            //设置确定按钮
            if (!TextUtils.isEmpty(positiveButtonText)) {
                btPositive.setText(positiveButtonText);
                if (onPositiveButtonClickListener != null) {
                    btPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onPositiveButtonClickListener.onClick(dialog, -1);
                        }
                    });
                }
            } else {
                btPositive.setVisibility(View.GONE);
                divider.setVisibility(View.GONE);
            }
            dialog.setCancelable(cancelable);
            dialog.setContentView(view);
            return dialog;
        }
    }
}
