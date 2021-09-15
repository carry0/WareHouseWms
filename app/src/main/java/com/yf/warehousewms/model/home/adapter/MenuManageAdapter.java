package com.yf.warehousewms.model.home.adapter;

import android.view.View;

import com.yf.common.base.BaseAdapter;
import com.yf.common.bean.AppAuthBean;
import com.yf.warehousewms.R;
import com.yf.warehousewms.databinding.ItemHomeMenuBinding;

/**
 * @Author
 * @cerate 2021/9/6 11:40
 **/
public class MenuManageAdapter extends BaseAdapter<ItemHomeMenuBinding, AppAuthBean> {

    @Override
    public int initLayout() {
        return R.layout.item_home_menu;
    }

    @Override
    protected void dataNull(ItemHomeMenuBinding binding, int position) {

    }

    @Override
    protected void dataCallBack(ItemHomeMenuBinding binding, AppAuthBean data, int position) {
        binding.butSelectMenu.setVisibility(data.getId()==0? View.GONE:View.VISIBLE);
        binding.butSelectMenu.setText(data.getAuth_name());
    }
}
