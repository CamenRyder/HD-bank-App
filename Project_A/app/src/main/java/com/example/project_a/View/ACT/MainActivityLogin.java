package com.example.project_a.View.ACT;

import android.os.Handler;

import com.example.project_a.R;
import com.example.project_a.View.FRG.m001_frg;
import com.example.project_a.ViewModel.mainVM;
import com.example.project_a.databinding.ActivityMainLoginBinding;
import com.example.project_a.databinding.ActivityMainLoginBinding;

public class MainActivityLogin extends baseACT<ActivityMainLoginBinding, mainVM> {

    @Override
    protected int getIDmain() {
        return R.id.ln_main01;
    }


    @Override
    protected void initViews() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showFragment(m001_frg.class.getName(),null,false);

                }
            },2000);
//        showFragment(m001_frg.class.getName(),null,false);
    }

    @Override
    protected Class<mainVM> ClassViewModel() {
        return mainVM.class;
    }

    @Override
    protected ActivityMainLoginBinding initViewBinding() {
        return ActivityMainLoginBinding.inflate(getLayoutInflater());
    }
}