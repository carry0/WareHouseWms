package com.yf.common;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @Author
 * @cerate 2021/9/28 14:50
 **/
public class AppCommon extends Application {

    private static AppCommon INSTANCE;

    public static AppCommon getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        //日志
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

}
