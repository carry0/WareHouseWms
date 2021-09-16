package com.yf.warehousewms.model.home.adjust.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.alibaba.fastjson.JSONObject;
import com.yf.common.bean.Carrier;
import com.yf.warehousewms.model.home.adjust.api.AdjustRepository;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @cerate 2021/9/6 16:56
 **/
public class AdjustViewModel extends AndroidViewModel {
    private final MutableLiveData<List<String>> listLiveData = new MutableLiveData<>();
    AdjustRepository repository = AdjustRepository.getAdjustRepository();

    public AdjustViewModel(@NonNull Application application) {
        super(application);

    }

    public MutableLiveData<List<String>> getListLiveData() {
        if (listLiveData.getValue() == null) {
            listLiveData.setValue(new ArrayList<>());
        }
        return listLiveData;
    }

    public MutableLiveData<JSONObject> putAway(int type, int id) {
        return repository.putAway(id, type, getListLiveData().getValue());
    }

    public MutableLiveData<Carrier> getCarrier() {
        return repository.getCarrier("");
    }

    public MutableLiveData<JSONObject> havingLocation() {
        return repository.havingLocation("");
    }
}
