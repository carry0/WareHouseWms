package com.yf.common.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

/**
 * @Author
 * @cerate 2021/9/3 12:03
 **/
public abstract class BaseAdapter<V extends ViewDataBinding, T> extends ListAdapter<T, BaseHolderView<V>> {
    protected OnItemClickListener<T> onItemClickListener;
    V binding;

    protected BaseAdapter() {
        super(new DiffUtil.ItemCallback<T>() {
            @Override
            public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return false;
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BaseHolderView<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, initLayout(), parent, false);
        return new BaseHolderView<>(binding);
    }

    /**
     * 绑定布局id
     *
     * @return int
     */
    public abstract @LayoutRes
    int initLayout();

    @Override
    public void onBindViewHolder(@NonNull BaseHolderView<V> holder, int position) {
        if (null == getItem(position)) {
            dataNull(holder.getBinding(), position);
        } else {
            dataCallBack(holder.getBinding(), getItem(position), position);
        }
        holder.getBinding().executePendingBindings();
    }

    /**
     * null数据回调
     *
     * @param binding  绑定
     * @param position 下标
     */
    protected abstract void dataNull(V binding, int position);

    /**
     * 正常返回
     *
     * @param binding  绑定
     * @param data     数据
     * @param position 下标
     */
    protected abstract void dataCallBack(V binding, T data, int position);

}
