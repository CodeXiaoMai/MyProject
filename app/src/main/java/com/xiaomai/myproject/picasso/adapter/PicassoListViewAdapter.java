package com.xiaomai.myproject.picasso.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaomai.myproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XiaoMai on 2017/2/8.
 */
public class PicassoListViewAdapter extends BaseAdapter {

    private Context context;
    private String[] images;

    public PicassoListViewAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_picasso_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvPicassoItem.setText("item" + (position + 1));
        Picasso.with(context)
                .load(images[position])
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(holder.ivPicassoItem);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_picasso_item)
        ImageView ivPicassoItem;
        @BindView(R.id.tv_picasso_item)
        TextView tvPicassoItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
