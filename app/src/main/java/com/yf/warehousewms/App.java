package com.yf.warehousewms;

import android.app.Application;

/**
 * @Author
 * @cerate 2021/9/2 14:59
 **/
public class App extends Application {

    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
