package com.example.project_a.View.FRG;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_a.API.Res.LoginRes;
import com.example.project_a.API.Res.RegisterRes;
import com.example.project_a.R;
import com.example.project_a.Storage.App;
import com.example.project_a.ViewModel.m003_VM;
import com.example.project_a.databinding.M003ResigerBinding;

public class m003_frg extends baseFRG<M003ResigerBinding, m003_VM> {
    @Override
    protected void initViews() {
            binding.btCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.showFragment(m001_frg.class.getName(),null,true);
                }
            });
            binding.btResiger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressResiger();
                }
            });
    }

    private void progressResiger() {
        viewModel.phone  = binding.edtPhone.getText().toString() ;
        viewModel.email  = binding.edtEmail.getText().toString() ;
        viewModel.fullName  = binding.edtFullname.getText().toString() ;
        viewModel.identity  = binding.edtIdentity.getText().toString() ;

        if(binding.edtUn.getText().toString() .isEmpty() || binding.edtPw.getText().toString().isEmpty()
                || viewModel.phone.isEmpty() || viewModel.email.isEmpty()
                || viewModel.fullName.isEmpty() || viewModel.identity.isEmpty())
        {
            Toast.makeText(context, "Please, input full component!", Toast.LENGTH_SHORT).show();
            return ;
        }
        App.getInstance().getStorage().username = binding.edtUn.getText().toString() ;
        App.getInstance().getStorage().passsword = binding.edtPw.getText().toString() ;
        viewModel.getResiger();

    }

    @Override
    public void apiSuccess(String key, Object data) {
            if(key.equals(m003_VM.REGISTER_KEY)) {
//
                RegisterRes res = (RegisterRes) data;
                Log.e(m003_frg.class.getName(), "Meesge: " + res.response.responseMessage);
                Log.e(m003_frg.class.getName(), "Meesge: " + res.response.responseCode);
                Log.e(m003_frg.class.getName(), "Meesge: " + res.response.responseId);
                Log.e(m003_frg.class.getName(), "Meesge: " + res.response.responseTime);


                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_m001);
                Window window = dialog.getWindow();
                if (window == null) return;
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                WindowManager.LayoutParams windowAttributes = window.getAttributes();
                windowAttributes.gravity = Gravity.CENTER;
                window.setAttributes(windowAttributes);
                dialog.setCancelable(true);
                TextView showIn4 = dialog.findViewById(R.id.tv_text);
                showIn4.setText(App.getInstance().getStorage().notifyCase);
                if (!res.response.responseCode.equals("00")) {
                    dialog.show();
                } else {
                    Toast.makeText(context, "Dang ki thanh cong!", Toast.LENGTH_SHORT).show();
                    viewModel.getAccountNO();
                }

            }
    }

    @Override
    protected Class<m003_VM> ClassVM() {
        return m003_VM.class;
    }

    @Override
    protected M003ResigerBinding initViewBindings(LayoutInflater inflater, ViewGroup container) {
        return M003ResigerBinding.inflate(inflater,container,false);
    }
}
