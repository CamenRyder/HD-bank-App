package com.example.project_a.otherClass;

import static com.example.project_a.ViewModel.RSAUtil.encrypt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.example.project_a.API.API;
import com.example.project_a.API.Req.resetPwReq;
import com.example.project_a.API.Res.resetPwRes;
import com.example.project_a.R;
import com.example.project_a.Storage.App;
import com.example.project_a.ViewModel.BaseViewModel_API;
import com.example.project_a.databinding.LayoutResetpwBinding;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class resetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resetpw);

        initView() ;
    }
            String credential ;
           private static String encrypt = "" ;
    private void initView() {
        EditText edt_US = findViewById(R.id.edt_userName) ;
        EditText edt_oldPW = findViewById(R.id.edt_oldPW) ;
        EditText edt_newPW = findViewById(R.id.edt_newPW) ;
        Button bt_check = findViewById(R.id.bt_ChangePW) ;
        bt_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(resetPassword.class.getName(),"Nhan su kien button");
                credential = "{\"username\":\""+ App.getInstance().getStorage().username+ "\",\"oldPass\":\""+edt_oldPW.getText().toString()+"\",\"newPass\":\""+edt_newPW.getText().toString()+"\"\n}";
                Log.e(resetPassword.class.getName(),"Nhan su kien: "+credential );
                Log.e(resetPassword.class.getName(),"Nhan su kien"+edt_oldPW.getText().toString());
                Log.e(resetPassword.class.getName(),"Nhan su kien"+edt_newPW.getText().toString());
                try {
                     encrypt = Base64.getEncoder().encodeToString(encrypt(credential, App.getInstance().getStorage().key));
                    Log.e(resetPassword.class.getName(),"RSA: "+encrypt);
                    setAPI();
                } catch (Exception e) {
                   e.printStackTrace();
            }
        }
        });


        }

    private void setAPI() {
            Retrofit rs = new Retrofit.Builder().baseUrl(BaseViewModel_API.BASER_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build() ;
        API api = rs.create(API.class) ;
            api.resetPW(new resetPwReq(encrypt)).enqueue(new Callback<resetPwRes>() {
                @Override
                public void onResponse(Call<resetPwRes> call, Response<resetPwRes> response) {
                    if(response.code() == 200 || response.code() == 201)
                    {
                        Log.e(resetPassword.class.getName(),response.body().response.responseCode);
                        Log.e(resetPassword.class.getName(),response.body().response.responseMessage);

                    }

                }

                @Override
                public void onFailure(Call<resetPwRes> call, Throwable t) {
                            Log.e(resetPassword.class.getName(),"Bi sai gi do roi!") ;
                }
            });
    }


}
