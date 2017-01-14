
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XiaoMai on 2017/1/13 19:26.
 */
public class MultiSelectListViewDemoActivity extends BaseActivity {

    private ListView mListView;

    private List<Map<String, Object>> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", String.valueOf(i));
            map.put("select", false);
            mDatas.add(map);
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
            Holder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_muilt_select,
                        parent, false);
                holder = new Holder();
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.title.setText(mDatas.get(position).get("title").toString());
            holder.checkBox.setChecked((boolean) mDatas.get(position).get("select"));
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mDatas.get(position).put("select", isChecked);
                }
            });
            return convertView;
        }

        class Holder {
            TextView title;

            CheckBox checkBox;
        }
    }
}
