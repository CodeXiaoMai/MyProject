
package com.xiaomai.myproject.adapter;

import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class DragListAdapter extends BaseAdapter {

    public List<JSONObject> mCopyList = new ArrayList<JSONObject>();

    public List<JSONObject> mDragDataList = new ArrayList<JSONObject>();

    public int mDropPosition = -1;

    public boolean mDataChanged = false;

    public boolean mDropItemVisible = false;

    public boolean isSameDragDirection = true;

    public int mLastFlag = -1;

    public int mItemHeight;

    public int dragPosition = -1;

    public Context context;

    public boolean isHidden;

    public DragListAdapter(List<JSONObject> dataList) {
        mDragDataList = dataList;
    }

    public List<JSONObject> getList() {
        return mDragDataList;
    }

    public void showDropItem(boolean showItem) {
        this.mDropItemVisible = showItem;
    }

    public void setInvisiblePosition(int position) {
        mDropPosition = position;
    }

    public void exchange(int startPosition, int endPosition) {
        // holdPosition = endPosition;
        JSONObject startObject = null;
        if (startPosition >= mDragDataList.size()) {
            startObject = null;
        } else {
            mDragDataList.get(startPosition);
        }
        if (startPosition < endPosition) {
            mDragDataList.add(endPosition + 1, startObject);
            mDragDataList.remove(startPosition);
        } else {
            mDragDataList.add(endPosition, startObject);
            mDragDataList.remove(startPosition + 1);
        }
        mDataChanged = true;
    }

    public void exchangeCopy(int startPosition, int endPosition) {
        JSONObject startObject = getCopyItem(startPosition);
        if (startPosition < endPosition) {
            mCopyList.add(endPosition + 1, startObject);
            mCopyList.remove(startPosition);
        } else {
            mCopyList.add(endPosition, startObject);
            mCopyList.remove(startPosition + 1);
        }
        mDataChanged = true;
    }

    public JSONObject getCopyItem(int position) {
        return mCopyList.get(position);
    }

    public void addDragItem(int start, JSONObject obj) {
        mDragDataList.remove(start);
        mDragDataList.add(start, obj);
    }

    public void copyList() {
        mCopyList.clear();
        for (JSONObject str : mDragDataList) {
            mCopyList.add(str);
        }
    }

    public void pastList() {
        mDragDataList.clear();
        for (JSONObject str : mCopyList) {
            mDragDataList.add(str);
        }
    }

    public void setIsSameDragDirection(boolean value) {
        isSameDragDirection = value;
    }

    public void setLastFlag(int flag) {
        mLastFlag = flag;
    }

    public void setHeight(int value) {
        mItemHeight = value;
    }

    public void setCurrentDragPosition(int position) {
        dragPosition = position;
    }

    public Animation getFromSelfAnimation(int x, int y) {
        TranslateAnimation go = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.ABSOLUTE, x, Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, y);
        go.setInterpolator(new AccelerateDecelerateInterpolator());
        go.setFillAfter(true);
        go.setDuration(100);
        go.setInterpolator(new AccelerateInterpolator());
        return go;
    }

    public Animation getToSelfAnimation(int x, int y) {
        TranslateAnimation go = new TranslateAnimation(Animation.ABSOLUTE, x,
                Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, y, Animation.RELATIVE_TO_SELF, 0);
        go.setInterpolator(new AccelerateDecelerateInterpolator());
        go.setFillAfter(true);
        go.setDuration(100);
        go.setInterpolator(new AccelerateInterpolator());
        return go;
    }
}
