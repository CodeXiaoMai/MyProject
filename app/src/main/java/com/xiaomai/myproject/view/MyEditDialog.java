package com.xiaomai.myproject.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaomai.myproject.R;

/**
 * Created by XiaoMai on 2016/9/5.
 */
public class MyEditDialog extends Dialog {

    public MyEditDialog(Context context) {
        super(context);
    }

    public MyEditDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyEditDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {

        public interface onPositiveButtonClickListener{
            void onClick(MyEditDialog dialog, String input);
        }

        private Context context;
        private String title;
        private String hint;
        private String input;
        private String positiveButtonText;
        private String negativeButtonText;
        private onPositiveButtonClickListener onPositiveButtonClickListener;
        private OnClickListener onNegativeButtonClickListener;
        private boolean cancelable;

        public Builder(Context context) {
            this.context = context;
            positiveButtonText = context.getResources().getString(R.string.button_ok);
            negativeButtonText = context.getResources().getString(R.string.button_cancel);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(int resId) {
            this.title = context.getResources().getString(resId);
            return this;
        }

        public Builder setHint(String hint) {
            this.hint = hint;
            return this;
        }

        public Builder setHint(int resId) {
            this.hint = context.getResources().getString(resId);
            return this;
        }

        public Builder setInput(String input) {
            this.input = input;
            return this;
        }

        public Builder setInput(int resId) {
            this.input = context.getResources().getString(resId);
            return this;
        }

        public Builder setPositiveButtonText(String positiveButtonText) {
            this.positiveButtonText = positiveButtonText;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder setOnPositiveButtonClickListener(onPositiveButtonClickListener onPositiveButtonClickListener) {
            this.onPositiveButtonClickListener = onPositiveButtonClickListener;
            return this;
        }

        public Builder setOnNegativeButtonClickListener(OnClickListener onNegativeButtonClickListener) {
            this.onNegativeButtonClickListener = onNegativeButtonClickListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public MyEditDialog onCreate() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final MyEditDialog dialog = new MyEditDialog(context, R.style.MyDialogWithOutTitle);
            View view = inflater.from(context).inflate(R.layout.my_edit_dialog, null);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            final EditText etInput = (EditText) view.findViewById(R.id.et_input);
            Button btPositive = (Button) view.findViewById(R.id.bt_positive);
            Button btNegative = (Button) view.findViewById(R.id.bt_negative);
            if (!TextUtils.isEmpty(title)) {
                tvTitle.setText(title);
            }
            if (!TextUtils.isEmpty(hint)) {
                etInput.setHint(hint);
            }
            if (!TextUtils.isEmpty(input)) {
                etInput.setText(input);
            }
            if (!TextUtils.isEmpty(positiveButtonText)) {
                btPositive.setText(positiveButtonText);
                if (onPositiveButtonClickListener != null) {
                    btPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onPositiveButtonClickListener.onClick(dialog,etInput.getText().toString());
                        }
                    });
                }else {
                    dialog.dismiss();
                }
            }
            if (!TextUtils.isEmpty(negativeButtonText)) {
                btNegative.setText(negativeButtonText);
                if (onNegativeButtonClickListener != null) {
                    btNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onNegativeButtonClickListener.onClick(dialog, -1);
                        }
                    });
                }else {
                    btNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
            dialog.setContentView(view);
            dialog.setCancelable(cancelable);
            return dialog;
        }
    }
}
