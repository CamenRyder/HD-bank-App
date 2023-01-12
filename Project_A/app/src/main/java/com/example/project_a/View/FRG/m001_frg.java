package com.example.project_a.View.FRG;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_a.API.Res.GetKey;
import com.example.project_a.API.Res.LoginRes;
import com.example.project_a.R;
import com.example.project_a.Storage.App;
import com.example.project_a.Storage.Storage;
import com.example.project_a.View.ACT.MainActivityHome;
import com.example.project_a.View.ACT.MainActivityLogin;
import com.example.project_a.ViewModel.m001_VM;
import com.example.project_a.databinding.M001LoginBinding;

public class m001_frg extends baseFRG<M001LoginBinding, m001_VM> {
    @Override
    protected void initViews()
    {
        viewModel.get_Key();
                binding.btLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.LoginAcc(binding.edtUn.getText().toString(),binding.edtPw.getText().toString());
                    }
                });
        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                callBack.showFragment(m003_frg.class.getName(),null,true);
            }
        });
    }

    @Override
    protected Class<m001_VM> ClassVM() {
        return m001_VM.class;
    }

    @Override
    protected M001LoginBinding initViewBindings(LayoutInflater inflater, ViewGroup container) {
        return M001LoginBinding.inflate(inflater,container,false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
            if(key.equals(m001_VM.GET_KEY))
            {
                GetKey getKey = (GetKey) data ;
                App.getInstance().getStorage().key = getKey.data.key ;
            }if(key.equals(m001_VM.LOGIN_ACCOUNT)) {
                        handleLogin(data) ;
        }
    }

    private void handleLogin( Object data) {
        LoginRes res = (LoginRes) data;
        if (res.response.responseCode.equals("00"))
        {
            App.getInstance().getStorage().accountNo = res.data.accountNo ;
            startActivity(new Intent(context, MainActivityHome.class));

        }

        else {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_m001);
            Window window = dialog.getWindow() ;
            if(window == null) return ;
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams windowAttributes = window.getAttributes() ;
            windowAttributes.gravity = Gravity.CENTER;
            window.setAttributes(windowAttributes);
            dialog.setCancelable(true);
            TextView showIn4 = dialog.findViewById(R.id.tv_text);
            if (res.response.responseCode.equals("03"))
                showIn4.setText(R.string.Error_03);
            else showIn4.setText(R.string.Error_01);
            dialog.show();

            //                    dialog.dismiss();

        }
    }


}
