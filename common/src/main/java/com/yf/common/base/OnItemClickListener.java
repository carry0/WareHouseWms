package com.yf.common.base;

import android.view.View;

/**
 * @Author
 * @cerate 2021/9/6 16:43
 **/
public interface OnItemClickListener<T> {
    /**
     *
     * @param view view
     * @param data T
     * @param position 下标
     */
    void onItemClick(View view,T data, int position);

}
