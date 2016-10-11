package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.adapter.MyListViewAdapter;
import com.xiaomai.myproject.entity.MyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoMai on 2016/8/29.
 */
public class MyMultiItemListViewDemoActivity extends Activity {

    private ListView mListView;
    private MyListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_listview);
        initViews();
    }

    private void initViews() {
        mListView = (ListView) findViewById(R.id.lv_main);
        List<MyItem> list = new ArrayList<>();
        MyItem item;
        for (int i = 1; i < 100; i++) {
            item = new MyItem(i % 3, i + "", R.mipmap.ic_launcher);
            list.add(item);
        }
        mAdapter = new MyListViewAdapter(this, list);
        mListView.setAdapter(mAdapter);
    }
}
