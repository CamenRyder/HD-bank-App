package com.example.project_a.View.FRG;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.project_a.ViewModel.BaseViewModel_API;
import com.example.project_a.databinding.M000loadingBinding;

public class m000_loading extends baseFRG<M000loadingBinding, BaseViewModel_API> {
    @Override
    protected void initViews() {

    }

    @Override
    protected Class<BaseViewModel_API> ClassVM() {
        return BaseViewModel_API.class;
    }

    @Override
    protected M000loadingBinding initViewBindings(LayoutInflater inflater, ViewGroup container) {
        return M000loadingBinding.inflate(inflater,container,false);
    }
}
