
package com.xiaomai.myproject.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xiaomai.myproject.R;

import java.util.List;

/**
 * Created by XiaoMai on 2016/12/14 13:20.
 */
public class ListDialog extends Dialog {
    public ListDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public static class Builder {

        private Context context;

        private boolean cancelable = true;

        private List<String> list;

        private onItemClickListener listener;

        public Builder(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOnItemClickListener(onItemClickListener listener) {
            this.listener = listener;
            return this;
        }

        public ListDialog create() {
            final ListDialog dialog = new ListDialog(context, R.style.MyDialogWithOutTitle);
            View layout = LayoutInflater.from(context).inflate(R.layout.list_dialog, null);
            final ListView listView = (ListView) layout.findViewById(R.id.listview);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                    dialog.dismiss();
                }
            });
            listView.setAdapter(
                    new ArrayAdapter<>(context, R.layout.list_item_dialog, R.id.textview, list));
            dialog.setCancelable(cancelable);
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
