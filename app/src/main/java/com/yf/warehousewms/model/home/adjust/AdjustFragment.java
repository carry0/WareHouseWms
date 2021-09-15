package com.yf.warehousewms.model.home.adjust;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yf.common.base.BaseFragment;
import com.yf.warehousewms.databinding.FragmentAdjustBinding;

/**
 * 倒仓上架
 * @author lwr 2021-06-02
 */
public class AdjustFragment extends BaseFragment<FragmentAdjustBinding> {


    @Override
    protected FragmentAdjustBinding setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return FragmentAdjustBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
