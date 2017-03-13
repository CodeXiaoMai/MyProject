
package com.xiaomai.myproject.largeimage.listener;

/**
 * Created by HSEDU on 2017/3/13 11:36.
 */

public interface OnMoveGestureListener {
    boolean onMoveBegin(MoveGestureDetector detector);

    boolean onMove(MoveGestureDetector detector);

    void onMoveEnd(MoveGestureDetector detector);
}
