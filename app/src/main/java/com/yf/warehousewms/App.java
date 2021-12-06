package com.yf.warehousewms;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yf.common.AppCommon;

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
    }
}
