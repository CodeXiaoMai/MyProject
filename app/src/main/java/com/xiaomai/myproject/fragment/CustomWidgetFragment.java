
package com.xiaomai.myproject.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.adapter.CommonFrameFragmentAdapter;
import com.xiaomai.myproject.base.BaseFragment;
import com.xiaomai.myproject.view.MyToast;
import com.xiaomai.myproject.wheelpicker.activity.WheelPickerViewActivity;

/**
 * Created by XiaoMai on 2017/2/7 4:05.
 */
public class CustomWidgetFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listView;

    private String[] datas;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_custom_widget, null);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        datas = new String[] {
                "WheelPickerView"
        };
    }

    @Override
    protected void loadData() {
        super.loadData();
        listView.setAdapter(new CommonFrameFragmentAdapter(datas));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        String data = datas[position].toLowerCase();
        if ("wheelpickerview".equals(data)) {
            intent = new Intent(mContext, WheelPickerViewActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        } else {
            MyToast.show(mContext, data);
        }
    }
}
