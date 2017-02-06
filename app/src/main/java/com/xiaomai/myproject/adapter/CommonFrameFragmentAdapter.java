
package com.xiaomai.myproject.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaomai.myproject.utils.DisplayUtils;

/**
 * Created by XiaoMai on 2017/2/5 15:06.
 */
public class CommonFrameFragmentAdapter extends BaseAdapter {

    private String[] datas;

    public CommonFrameFragmentAdapter(String[] datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.length;
    }

    @Override
    public Object getItem(int position) {
        return datas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        TextView textView = new TextView(context);
        textView.setHeight(DisplayUtils.dip2px(context, 45));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setPadding(10, 10, 10, 10);
        textView.setText(getItem(position).toString());
        return textView;
    }

}
