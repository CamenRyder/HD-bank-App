package com.example.project_a.API.Req;

import com.example.project_a.Storage.App;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class resetPwReq implements Serializable {
    @SerializedName("data")
    public data data ;
    @SerializedName("request")
    public request request ;



    public static class request
    {
        @SerializedName("requestId")
        public String requestId ;
        @SerializedName("requestTime")
        public String requestTime ;

        public request(String requestId, String requestTime) {
            this.requestId = requestId;
            this.requestTime = requestTime;
        }
    }


    public static class data
    {
        @SerializedName("credential")
        public String credential ;
        @SerializedName("key")
        public String key ;

        public data(String credential, String key) {
            this.credential = credential;
            this.key = key;
        }
    }

    public resetPwReq(String credential) {
        this.data = new data(credential, App.getInstance().getStorage().key);
        this.request = new request("a7ea23df-7468-439d-9b12-26eb4a760901","1667200102200");
    }
}
