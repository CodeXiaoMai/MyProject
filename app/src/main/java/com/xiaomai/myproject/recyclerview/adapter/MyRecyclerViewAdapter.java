
package com.xiaomai.myproject.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomai.myproject.R;

import java.util.List;

/**
 * Created by XiaoMai on 2017/2/9 14:33.
 */
public class MyRecyclerViewAdapter
        extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private Context context;

    private List<String> datas;

    /**
     * 添加数据
     * 
     * @param position
     * @param data
     */
    public void addData(int position, String data) {
        datas.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 移除数据
     * 
     * @param position
     */
    public void removeData(int position) {
        // 防止数组越界
        if (datas.size() > position) {
            datas.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 点击RecyclerView某条的监听
     */
    public interface OnItemClickListener {
        void onItemClick(View view, String data);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyRecyclerViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    /**
     * 相当于getView方法中创建View和ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_recyclerview, null);
        return new MyViewHolder(itemView);
    }

    /**
     * 数据和View绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // 根据位置得到对应的数据
        String data = datas.get(position);
        holder.tv_title.setText(data);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_icon;

        private TextView tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(v, datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}
