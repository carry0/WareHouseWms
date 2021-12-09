package com.yf.loadview;

import android.app.Application;


/**
 * @Author
 * @cerate 2021/9/28 14:50
 **/
public class AppLoad extends Application {

    private static AppLoad INSTANCE;

    public static AppLoad getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

}
