package com.example.project_a.View.FRG;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.project_a.ViewModel.CommonVm;
import com.example.project_a.databinding.M002MainTestBinding;

public class m002_frg_demo extends baseFRG<M002MainTestBinding, CommonVm>
{
    @Override
    protected void initViews() {

    }

    @Override
    protected Class<CommonVm> ClassVM() {
        return CommonVm.class;
    }

    @Override
    protected M002MainTestBinding initViewBindings(LayoutInflater inflater, ViewGroup container) {
        return M002MainTestBinding.inflate(inflater,container,false);
    }
}
