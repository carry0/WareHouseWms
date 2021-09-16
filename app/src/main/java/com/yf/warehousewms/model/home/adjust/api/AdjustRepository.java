package com.yf.warehousewms.model.home.adjust.api;

import androidx.lifecycle.MutableLiveData;

import com.alibaba.fastjson.JSONObject;
import com.yf.common.api.ServerApi;
import com.yf.common.bean.Carrier;
import com.yf.network.NetWorkUtil;
import com.yf.network.callback.RequestCallback;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author
 * @cerate 2021/9/15 15:30
 **/
public class AdjustRepository {
    private static WeakReference<AdjustRepository> weakReference;
    AdjustApi api;

    public AdjustRepository() {
        api = NetWorkUtil.getInstall().createService(AdjustApi.class);
    }

    public static AdjustRepository getAdjustRepository() {
        if (weakReference == null) {
            synchronized (AdjustRepository.class) {
                if (weakReference == null) {
                    weakReference = new WeakReference<>(new AdjustRepository());
                }
            }
        }
        return weakReference.get();
    }

    public MutableLiveData<JSONObject> putAway(int id, int type, List<String> list) {
        Map<String, Object> map = new HashMap<>(0);
//        map.put("userId", id);
//        map.put("data",list);
        map.put("type", type);
        return RequestCallback.doNoRequestHead(api.putAway(map));
    }

    public MutableLiveData<Carrier> getCarrier(String json) {
        return RequestCallback.doNoRequestHead(api.getCarrier(json));
    }

    public MutableLiveData<JSONObject> havingLocation(String json) {
        return RequestCallback.doNoRequestHead(api.havingLocation(json));
    }

    interface AdjustApi extends ServerApi {
        @POST("/shYf/sh/count/havingLocation")
        Observable<JSONObject> havingLocation(@Body String json);
    }
}
