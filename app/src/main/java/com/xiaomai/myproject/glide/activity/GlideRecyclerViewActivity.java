
package com.xiaomai.myproject.glide.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.glide.adapter.GlideRecyclerViewAdapter;

import butterknife.BindView;

public class GlideRecyclerViewActivity extends BaseActivity {

    @BindView(R.id.rv_glide)
    RecyclerView rvGlide;

    private String[] mDatas;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_glide_recycler_view;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("Glide在RecyclerView中加载图片");
        rvGlide.setAdapter(new GlideRecyclerViewAdapter(mContext, mDatas));
        rvGlide.setLayoutManager(
                new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected int getCodeResId() {
        return R.string.code_glide_recycler_view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mDatas = new String[] {
                "http://b337.photo.store.qq.com/psb?/V10FcMmY1Ttz2o/7.fo01qLQ*SI59*E2Wq.j82HuPfes*efgiyEi7mrJdk!/b/dLHI5cioAQAA&bo=VQOAAgAAAAABB*Q!&rf=viewer_4",
                "http://b118.photo.store.qq.com/psb?/V10FcMmY2gHuOI/8*6eK6PHCNTx1utXooId*KAWgwPTllj.b6uBg4McCwM!/b/dAt8W0YJJAAA&bo=VQOAAgAAAAABB*Q!&rf=viewer_4",
                "http://img1.imgtn.bdimg.com/it/u=488611129,2377736106&fm=11&gp=0.jpg",
                "http://img2.imgtn.bdimg.com/it/u=3398443685,2594061265&fm=11&gp=0.jpg",
                "http://img3.imgtn.bdimg.com/it/u=2271902832,1324672617&fm=21&gp=0.jpg",
                "http://a.hiphotos.baidu.com/image/h%3D200/sign=d20242020e24ab18ff16e63705fae69a/267f9e2f070828389f547b30bf99a9014c08f1bd.jpg",
                "http://img5.duitang.com/uploads/item/201406/28/20140628132554_UNE4n.thumb.700_0.jpeg",
                "http://cdn.duitang.com/uploads/item/201309/22/20130922202150_ntvAB.thumb.600_0.jpeg",
                "http://cdn.duitang.com/uploads/item/201208/04/20120804013554_yRGfe.jpeg",
                "http://img5.imgtn.bdimg.com/it/u=2050390856,2980742959&fm=21&gp=0.jpg",
                "http://img3.duitang.com/uploads/item/201501/23/20150123204322_N8nw5.jpeg",
                "http://img4q.duitang.com/uploads/item/201505/09/20150509204813_nEwxF.jpeg",
                "http://img1.imgtn.bdimg.com/it/u=2432702027,3704029716&fm=21&gp=0.jpg",
                "http://i.imgur.com/syELajx.jpg", "http://i.imgur.com/COzBnru.jpg",
                "http://i.imgur.com1111/Z3QjilA.jpg"
        };
    }
}
