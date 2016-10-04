package com.xiaomai.myproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.entity.MyItem;

import java.util.List;

/**
 * Created by XiaoMai on 2016/8/29.
 */
public class MyListViewAdapter extends BaseAdapter {

    //只有标题
    private final int TYPE_ONLY_TITLE = 0;
    //既有标题又有图片
    private final int TYPE_TITLE_WITH_IMAGE = 1;
    //只有图片
    private final int TYPE_ONLY_IAMGE = 2;
    private List<MyItem> mList;
    private Context mContext;

    public MyListViewAdapter(Context mContext, List<MyItem> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 一定要重写此方法，并返回正确的ViewType总数
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = mList.get(position).getType();
        switch (viewType) {
            case TYPE_ONLY_TITLE:
                viewType = TYPE_ONLY_TITLE;
                break;
            case TYPE_TITLE_WITH_IMAGE:
                viewType = TYPE_TITLE_WITH_IMAGE;
                break;
            case TYPE_ONLY_IAMGE:
                viewType = TYPE_ONLY_IAMGE;
                break;
        }
        return viewType;
    }

    class ViewHolder1 {
        TextView tvTitle;
    }

    class ViewHolder2 {
        TextView tvTitle;
        ImageView ivDesc;
    }

    class ViewHolder3 {
        ImageView ivDesc;
    }

    /**
     * 首先判断当前item的布局类型，然后按照正常的去写
     * @param i
     * @param view
     * @param viewGroup
     * @return View
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == TYPE_ONLY_TITLE) {
            ViewHolder1 holder;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_main_list_type1, null);
                holder = new ViewHolder1();
                holder.tvTitle = (TextView) view.findViewById(R.id.tv_title);
                view.setTag(holder);
            } else {
                holder = (ViewHolder1) view.getTag();
            }
            holder.tvTitle.setText(mList.get(i).getTitle());
        } else if (itemViewType == TYPE_TITLE_WITH_IMAGE) {
            ViewHolder2 holder;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_main_list_type2, null);
                holder = new ViewHolder2();
                holder.tvTitle = (TextView) view.findViewById(R.id.tv_title);
                holder.ivDesc = (ImageView) view.findViewById(R.id.iv_desc);
                view.setTag(holder);
            } else {
                holder = (ViewHolder2) view.getTag();
            }
            MyItem item = mList.get(i);
            holder.tvTitle.setText(item.getTitle());
            holder.ivDesc.setImageResource(item.getResId());
        } else if (itemViewType == TYPE_ONLY_IAMGE) {
            ViewHolder3 holder;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_main_list_type3, null);
                holder = new ViewHolder3();
                holder.ivDesc = (ImageView) view.findViewById(R.id.iv_desc);
                view.setTag(holder);
            } else {
                holder = (ViewHolder3) view.getTag();
            }
            holder.ivDesc.setImageResource(mList.get(i).getResId());
        }
        return view;
    }
}
