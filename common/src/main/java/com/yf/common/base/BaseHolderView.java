package com.yf.common.base;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author
 * @cerate 2021/9/3 12:03
 **/
public class BaseHolderView<V extends ViewDataBinding> extends RecyclerView.ViewHolder {
    V binding;

    public V getBinding() {
        return binding;
    }

    public BaseHolderView(V itemView) {
        super(itemView.getRoot());
        this.binding = itemView;
    }

}
