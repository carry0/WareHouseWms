package com.yf.common.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author
 * @cerate 2021/9/3 12:03
 **/
public class BaseAdapter<V extends ViewDataBinding,T> extends RecyclerView.Adapter<BaseHolderView> {
    V binding;
    @NonNull
    @Override
    public BaseHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolderView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
