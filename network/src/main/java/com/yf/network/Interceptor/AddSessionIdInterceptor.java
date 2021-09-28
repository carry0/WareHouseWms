package com.yf.network.Interceptor;

import android.text.TextUtils;
import android.webkit.WebSettings;

import com.yf.common.AppCommon;
import com.yf.common.tool.ConfigManage;
import com.yf.common.tool.SpModel;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author s
 */
public class AddSessionIdInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        //获取到request
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        String userAgent = WebSettings.getDefaultUserAgent(AppCommon.getInstance());
        //获取到方法
        String method = request.method();
        //从SP中获取到已保存的sessionID
        SpModel app = SpModel.getInstance(AppCommon.getInstance(), ConfigManage.APP_TABLE);
        String sessionId = (String) app.getData(ConfigManage.SESSION_ID, "");
        //GET添加参数
        if (ConfigManage.GET.equals(method)) {
            HttpUrl.Builder builder = request.url().newBuilder()
                    .addQueryParameter("__ajax", "json");
            HttpUrl url = builder.build();
            requestBuilder.url(url);
        } else if (ConfigManage.POST.equals(method)) {
            //post请求追加参数
            FormBody.Builder newBody = new FormBody.Builder();
            RequestBody requestBody = request.body();
            if (requestBody instanceof FormBody) {
                FormBody formBody = (FormBody) requestBody;
                for (int i = 0; i < formBody.size(); i++) {
                    newBody.add(formBody.encodedName(i), formBody.encodedValue(i));
                }
                newBody.add("__ajax", "json");
                requestBuilder.post(newBody.build());
            }
        }
        requestBuilder.removeHeader("User-Agent")
                .addHeader("User-Agent", userAgent);
        if (!TextUtils.isEmpty(sessionId)) {
//            修改Cookie 、session id名称
            requestBuilder.addHeader("Cookie", "jeesite.session.id=" + sessionId);
        }
        request = requestBuilder.build();
        return chain.proceed(request);
    }
}
