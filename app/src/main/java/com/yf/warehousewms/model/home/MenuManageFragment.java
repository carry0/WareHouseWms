package com.yf.warehousewms.model.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yf.common.base.BaseFragment;
import com.yf.common.bean.LoginBean;
import com.yf.common.tool.ConfigManage;
import com.yf.common.tool.SpModel;
import com.yf.loadview.helper.DefaultLoadHelper;
import com.yf.warehousewms.App;
import com.yf.warehousewms.R;
import com.yf.warehousewms.databinding.FragmentMenuBinging;
import com.yf.warehousewms.model.home.adapter.MenuManageAdapter;
import com.yf.warehousewms.model.home.adjust.vm.AdjustViewModel;

import java.lang.ref.WeakReference;

/**
 * @Author
 * @cerate 2021/9/6 11:28
 **/
public class MenuManageFragment extends BaseFragment<FragmentMenuBinging> {
    WeakReference<MenuManageAdapter> weakReference;
    LoginBean loginBean;

    @Override
    protected FragmentMenuBinging setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return FragmentMenuBinging.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        weakReference = new WeakReference<>(new MenuManageAdapter());
        binding.rvHome.setAdapter(weakReference.get());
    }

    @Override
    protected void initData() {
        defaultLoadHelper.getDefaultIml().showBadNetworkView(v -> {
            Logger.d("刷新");
        });
        String s = (String) SpModel.getInstance(App.getInstance(),
                ConfigManage.APP_TABLE).getData(ConfigManage.HOME_MENU, ConfigManage.HOME_MENU);
        loginBean = new Gson().fromJson(s, LoginBean.class);
        weakReference.get().submitList(loginBean.getApp_auth());
        weakReference.get().setOnItemClickListener((view, data, position) -> navigate(R.id.action_to_nav_model));
    }

    @Override
    protected void onBackPressed() {
        requireActivity().finish();
    }

}
