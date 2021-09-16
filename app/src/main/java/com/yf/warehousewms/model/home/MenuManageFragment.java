package com.yf.warehousewms.model.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.yf.common.base.BaseFragment;
import com.yf.common.bean.LoginBean;
import com.yf.common.tool.ConfigManage;
import com.yf.common.tool.SpModel;
import com.yf.warehousewms.App;
import com.yf.warehousewms.databinding.FragmentMenuBinging;
import com.yf.warehousewms.model.home.adapter.MenuManageAdapter;

import java.lang.ref.WeakReference;

/**
 * @Author
 * @cerate 2021/9/6 11:28
 **/
public class MenuManageFragment extends BaseFragment<FragmentMenuBinging> {
    WeakReference<MenuManageAdapter> weakReference;
    MenuManageAdapter adapter;
    LoginBean loginBean;

    @Override
    protected FragmentMenuBinging setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return FragmentMenuBinging.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        weakReference = new WeakReference<>(new MenuManageAdapter());
        adapter = weakReference.get();
        binding.rvHome.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        String s = (String) SpModel.getInstance(App.getInstance(),
                ConfigManage.APP_TABLE).getData(ConfigManage.HOME_MENU, ConfigManage.HOME_MENU);
        loginBean = new Gson().fromJson(s, LoginBean.class);
        adapter.submitList(loginBean.getApp_auth());
    }

    @Override
    protected void onBackPressed() {
        requireActivity().finish();
    }

}
