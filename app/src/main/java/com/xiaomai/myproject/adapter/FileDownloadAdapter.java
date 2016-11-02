package com.xiaomai.myproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.entity.File;

import java.util.List;

/**
 * Created by XiaoMai on 2016/10/26 15:26.
 */
public class FileDownloadAdapter extends BaseAdapter {

    private List<File> mList;

    private Context context;

    public FileDownloadAdapter(List<File> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    public void setList(List<File> mList){
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_download, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.title.setText(mList.get(position).getTitle());
        holder.progressBar.setProgress(mList.get(position).getProgress());
        return convertView;
    }

    class Holder{
        TextView title;
        ProgressBar progressBar;
    }
}
