
package com.xiaomai.myproject.glide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xiaomai.myproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XiaoMai on 2017/2/10 10:30.
 */
public class GlideRecyclerViewAdapter
        extends RecyclerView.Adapter<GlideRecyclerViewAdapter.ViewHolder> {

    private Context context;

    private String[] datas;

    public GlideRecyclerViewAdapter(Context context, String[] datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_glide_recyclerview, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                context.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f,
                context.getResources().getDisplayMetrics());
        Glide.with(context).load(datas[position]).placeholder(R.drawable.ic_launcher)
                .error(R.mipmap.ic_launcher) // 出错的占位图
                .override(width, height) // 图片显示的分辨率，像素值可以转化为Dp再设置
                .animate(R.anim.glide_anim).centerCrop().fitCenter().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_glide_recycler_view)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
