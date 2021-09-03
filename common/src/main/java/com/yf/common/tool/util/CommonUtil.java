package com.yf.common.tool.util;

import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import com.yf.common.R;

import java.io.File;

/**
 * @Author
 * @cerate 2021/9/3 11:28
 **/
public class CommonUtil {
    /**
     * 获取版本号
     * @param context 上下文
     * @return 版本号
     */
    public static String getVersion(Context context){
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return  packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return  "";
    }

    /**
     * 下载管理
     */
    public static void downloadManage(String uri,Context context){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        //设置通知标题
        request.setTitle("更新程序");
        //设置通知标题message
        request.setDescription("新版程序下载中...");
        request.setVisibleInDownloadsUi(true);

        //设置文件存放路径
        File file = new File(Environment.getExternalStorageDirectory(), context.getResources().getString(R.string.download_apk_address));
        request.setDestinationUri(Uri.fromFile(file));

    }
}
