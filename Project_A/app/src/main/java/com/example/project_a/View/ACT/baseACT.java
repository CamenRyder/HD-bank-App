package com.example.project_a.View.ACT;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;


import com.example.project_a.View.FRG.baseFRG;
import com.example.project_a.View.onMainCallBack;

import java.lang.reflect.Constructor;

public abstract class baseACT<T extends ViewBinding, M extends ViewModel>
        extends AppCompatActivity implements onMainCallBack {
    T binding ;
    M viewModel ;

    @Override
    public void showFragment(String TAG, Object data, boolean isBack) {
        try {
            Class<?> clazz = Class.forName(TAG) ;
            Constructor<?> cons = clazz.getConstructor() ;
            baseFRG<?,?> frg = (baseFRG<?, ?>) cons.newInstance();
            frg.setCallBack(this);
            frg.setData(data);
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction() ;
                if(isBack)
                        trans.addToBackStack(null);
                    trans.replace(getIDmain(),frg,TAG).commit() ;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void toBackPrevious() {
        onBackPressed();
    }

    protected abstract int getIDmain();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        viewModel = new ViewModelProvider(this).get(ClassViewModel());
        initViews();
        setContentView(binding.getRoot());
    }

    protected abstract void initViews();

    protected abstract Class<M> ClassViewModel();

    protected abstract T initViewBinding();


}
