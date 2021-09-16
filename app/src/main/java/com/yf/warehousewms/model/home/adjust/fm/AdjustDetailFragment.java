package com.yf.warehousewms.model.home.adjust.fm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yf.common.base.BaseFragment;
import com.yf.warehousewms.databinding.FragmentAdjustBinding;
import com.yf.warehousewms.databinding.FragmentAdjustDetailBinding;

/**
 * 倒仓上架明细
 * @author lwr 2021-06-02
 */
public class AdjustDetailFragment extends BaseFragment<FragmentAdjustDetailBinding> {


    @Override
    protected FragmentAdjustDetailBinding setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return FragmentAdjustDetailBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
