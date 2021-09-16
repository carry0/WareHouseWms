package com.yf.warehousewms.model.home.adjust.fm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.fastjson.JSONObject;
import com.yf.common.base.BaseFragment;
import com.yf.warehousewms.R;
import com.yf.warehousewms.databinding.FragmentAdjustCarrierBinding;
import com.yf.warehousewms.model.home.adjust.vm.AdjustViewModel;


/**
 * @Author
 * @cerate 2021/9/15 16:40
 **/
public class AdjustCarrierFragment extends BaseFragment<FragmentAdjustCarrierBinding> {
    AdjustViewModel viewModel;

    @Override
    protected FragmentAdjustCarrierBinding setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return FragmentAdjustCarrierBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        viewModel = new ViewModelProvider(findController().getViewModelStoreOwner(R.id.nav_model)).get(AdjustViewModel.class);

    }

    @Override
    protected void initData() {
        binding.button2.setOnClickListener(v -> confirm());
    }

    private void confirm() {
        viewModel.havingLocation().observe(this,jsonObject -> {
            navigate(R.id.action_to_adjust);
        });
    }

    public void result(String epc) {
        if (epc.startsWith(getString(R.string.epc_code))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("epc", epc);
            String json = jsonObject.toJSONString();
            viewModel.getCarrier().observe(this, carrier -> {
                binding.etStorageCode.setText(carrier.getLocationEPC());
                binding.etTrayCode.setText(carrier.getTrayEPC());
            });
        }
    }
}
