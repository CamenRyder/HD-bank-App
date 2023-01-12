package com.example.project_a.ViewModel;

public interface onMainAPICallBack {
    void apiSuccess(String key, Object data) ;
    void apiError(String key,int code,Object data ) ;
}
