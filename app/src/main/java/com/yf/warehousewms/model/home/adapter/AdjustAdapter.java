package com.yf.warehousewms.model.home.adapter;

import android.view.View;

import com.yf.common.base.BaseAdapter;
import com.yf.common.bean.AppAuthBean;
import com.yf.warehousewms.R;
import com.yf.warehousewms.databinding.ItemAdjustBinding;
import com.yf.warehousewms.databinding.ItemHomeMenuBinding;

/**
 * @Author
 * @cerate 2021/9/6 11:40
 **/
public class AdjustAdapter extends BaseAdapter<ItemAdjustBinding, AppAuthBean> {

    @Override
    public int initLayout() {
        return R.layout.item_adjust;
    }

    @Override
    protected void dataNull(ItemAdjustBinding binding, int position) {

    }

    @Override
    protected void dataCallBack(ItemAdjustBinding binding, AppAuthBean data, int position) {
        binding.getRoot().setOnClickListener(v -> onItemClickListener.onItemClick(binding.getRoot(),data,position));
    }
}
