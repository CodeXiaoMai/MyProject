
package com.xiaomai.myproject.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.json.activity.JsonActivity;
import com.xiaomai.myproject.okhttp.activity.OKHttpActivity;
import com.xiaomai.myproject.recyclerview.activity.RecyclerViewActivity;
import com.xiaomai.myproject.adapter.CommonFrameFragmentAdapter;
import com.xiaomai.myproject.base.BaseFragment;

/**
 * Created by XiaoMai on 2017/2/5 11:44.
 */
public class CommonFrameFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private static final String TAG = CommonFrameFragment.class.getSimpleName();

    private ListView lvFragmentCommon;

    private String[] datas;

    @Override
    protected View initView() {
        Log.d(TAG, "initView: ");
        View view = View.inflate(mContext, R.layout.fragment_common_frame, null);
        lvFragmentCommon = (ListView) view.findViewById(R.id.lv_fragment_common);
        lvFragmentCommon.setOnItemClickListener(this);
        return view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        datas = new String[] {
                "JSON","RecyclerView", "OKHttp", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1",
                "2", "1", "2"
        };
    }

    @Override
    protected void loadData() {
        Log.d(TAG, "loadData: ");
        super.loadData();
        lvFragmentCommon.setAdapter(new CommonFrameFragmentAdapter(datas));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        String data = datas[position].toLowerCase();
        if ("okhttp".equals(data)) {
            intent = new Intent(mContext, OKHttpActivity.class);
        } else if ("recyclerview".equals(data)) {
            intent = new Intent(mContext, RecyclerViewActivity.class);
        } else if ("json".equals(data)){
            intent = new Intent(mContext, JsonActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(mContext, "data == " + datas[position], Toast.LENGTH_SHORT).show();
        }
    }
}
