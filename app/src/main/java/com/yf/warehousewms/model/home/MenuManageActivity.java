package com.yf.warehousewms.model.home;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.gson.Gson;
import com.yf.common.base.BaseActivity;
import com.yf.common.bean.LoginBean;
import com.yf.common.tool.ConfigManage;
import com.yf.common.tool.SpModel;
import com.yf.warehousewms.App;
import com.yf.warehousewms.R;
import com.yf.warehousewms.databinding.MenuManageBinding;

import java.util.Set;

import static androidx.navigation.ui.NavigationUI.navigateUp;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class MenuManageActivity extends BaseActivity<MenuManageBinding> {
    private final int[] itemId;
    private final Set<Integer> topDestinationIds;
    AppBarConfiguration appBarConfiguration;
    LoginBean loginBean;

    public MenuManageActivity() {
        itemId = new int[]{R.id.nav_item1, R.id.nav_item2, R.id.nav_item3, R.id.nav_item4, R.id.nav_item5,
                R.id.nav_item6, R.id.nav_item7, R.id.nav_item8, R.id.nav_item9, R.id.nav_item10, R.id.nav_item11,
                R.id.nav_item12, R.id.nav_item13, R.id.nav_item14, R.id.nav_item15, R.id.nav_item16, R.id.nav_item17,
                R.id.nav_item18, R.id.nav_item19, R.id.nav_about, R.id.nav_item22};

        topDestinationIds = Set.of(R.id.menuManageFragment);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_menu_manage;
    }

    @Override
    protected void initView() {
        String bean = (String) SpModel.getInstance(App.getInstance(),
                ConfigManage.APP_TABLE).getData(ConfigManage.HOME_MENU, ConfigManage.HOME_MENU);
        loginBean = new Gson().fromJson(bean, LoginBean.class);
        initNavigation();
        setMenuItemTitle();
    }

    /**
     * 初始化Navigation+NavigationView+Toolbar
     */
    private void initNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.menu_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener(this::onDestinationChanged);
    }

    /**
     * 控制修改器
     *
     * @param controller  nav控制
     * @param destination NavId
     * @param arguments   bundle参数
     */
    private void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
        boolean isHideToolbar = destination.getId() == R.id.menuManageFragment;
        binding.drawerLayout.setDrawerLockMode(!isHideToolbar ? DrawerLayout.LOCK_MODE_LOCKED_CLOSED : DrawerLayout.LOCK_MODE_UNLOCKED);
        appBarConfiguration = new AppBarConfiguration.Builder(controller.getGraph()).setOpenableLayout(binding.drawerLayout).build();
        setupWithNavController(binding.tbMain, controller, appBarConfiguration);
        setupWithNavController(binding.nvMain, controller);
        binding.tbMain.setSubtitle(isHideToolbar ?"操作人："+loginBean.getUsername():null);
        if (!isHideToolbar) {binding.tbMain.setNavigationOnClickListener(v -> onBackPressed());}
        //菜单中点击事件处理
        if (isHideToolbar) binding.nvMain.setNavigationItemSelectedListener(item -> {
            Log.i("TAG", "initListener: --------" + item.getTitle());
            //关闭抽屉
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            controller.navigate(R.id.action_to_nav_model);
            return false;
        });
    }

    /**
     * 设置菜单标题
     */
    private void setMenuItemTitle() {
        for (int i = 0; i < itemId.length; i++) {
            MenuItem menuItem = binding.nvMain.getMenu().findItem(itemId[i]);
            menuItem.setTitle(loginBean.getApp_auth().get(i).getAuth_name());
        }
    }

    @Override
    protected void initListener() {

    }

}