package com.example.project_a.ViewModel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.project_a.API.API;
import com.example.project_a.API.Res.LoginRes;
import com.example.project_a.R;
import com.example.project_a.Storage.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseViewModel_API extends ViewModel {
    public static final String BASER_URL = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/" ;
    protected final String TAG = BaseViewModel_API.class.getName() ;
    protected onMainAPICallBack callBack ;

    public void setCallBack(onMainAPICallBack callBack) {
        this.callBack = callBack;
    }

    protected final API getAPI()
    {
        Retrofit rs = new Retrofit.Builder().baseUrl(BASER_URL).addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS).build()).build() ;
        return rs.create(API.class);
    }

    protected <T>Callback<T> initHandleResponse(String key)
    {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if(response.code() == 200 || response.code() == 201)
                {
                    Log.e(key,response.message()) ;
                    assert response.body() != null ;
                    handleSuccess(key,response.body());
                }
                else handleFail(key, response.code(),response.errorBody()) ;
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                        Log.e(TAG,"onFailure:  "+ t.getMessage());
                        handleException(key,t);
            }
        };
    }

    private void handleException(String key, Throwable t) {
        Log.e(TAG,"handleException:  "+ t.getMessage());
        Log.e(TAG,"handleException:  "+ key);
    }

    protected void handleFail(String key, int code, ResponseBody errorBody) {
            Log.e(TAG, "HandleFail: " + code) ;
            callBack.apiError(key,999,errorBody);

    }

    protected  void handleSuccess(String key, Object body) {
        Log.e(TAG,"HandleSuccess: " + body) ;
        callBack.apiSuccess(key,body) ;
    }

}
