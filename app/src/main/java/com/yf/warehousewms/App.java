package com.yf.warehousewms;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yf.common.AppCommon;
import com.yf.common.loadviewhelper.load.LoadViewHelper;

import leakcanary.LeakCanary;

/**
 * @Author
 * @cerate 2021/9/2 14:59
 **/
public class App extends AppCommon {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.init(App.this);
//        initLoadingHelper();
    }

    /**
     * 初始化加载界面，空界面等
     */
    private void initLoadingHelper() {
        LoadViewHelper.getBuilder()
                .setLoadEmpty(R.layout.empty_view)
                .setLoadError(R.layout.error_view)
                .setLoadIng(R.layout.loading_view);
    }
}
