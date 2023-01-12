package com.example.project_a.View.FRG;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.project_a.ViewModel.m013_VM;
import com.example.project_a.databinding.M013NotificationBinding;

public class m013_frg extends baseFRG<M013NotificationBinding, m013_VM> {
    @Override
    protected void initViews() {
            // Nothing to do that
    }

    @Override
    protected Class<m013_VM> ClassVM() {
        return m013_VM.class;
    }

    @Override
    protected M013NotificationBinding initViewBindings(LayoutInflater inflater, ViewGroup container) {
        return M013NotificationBinding.inflate(inflater,container,false);
    }
}
