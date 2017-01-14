
package com.xiaomai.myproject.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.entity.MultiSelectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2017/1/13 19:49.
 */
public class MultiSelectListViewDemo2Activity extends BaseActivity {
    private ListView mListView;

    private List<MultiSelectBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            MultiSelectBean bean = new MultiSelectBean(String.valueOf(i), false);
            mDatas.add(bean);
        }
    }

    @Override
    protected void initViews() {
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new MyAdapter());
    }

    @Override
    protected int getContentLayout() {
        return R.layout.act_mult_select_listview;
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_muilt_select, parent,
                    false);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            title.setText(mDatas.get(position).getTitle());
            checkBox.setChecked((boolean) mDatas.get(position).isSelect());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mDatas.get(position).setSelect(isChecked);
                }
            });
            return convertView;
        }
    }
}
