package com.yf.warehousewms.model.home;

import androidx.appcompat.app.ActionBarDrawerToggle;

import com.yf.common.base.BaseActivity;
import com.yf.common.tool.ConfigManage;
import com.yf.common.tool.SpModel;
import com.yf.warehousewms.App;
import com.yf.warehousewms.R;
import com.yf.warehousewms.databinding.MenuManageBinding;

public class MenuManageActivity extends BaseActivity<MenuManageBinding> {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_menu_manage;
    }

    @Override
    protected void initView() {
        binding.tbMain.setSubtitle("操作人:" + SpModel.getInstance(App.getInstance(),
                ConfigManage.APP_TABLE).getData(ConfigManage.USER_NAME,"user"));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout,binding.tbMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void initListener() {

    }
}