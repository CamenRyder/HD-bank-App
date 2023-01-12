package com.example.project_a.API.Req;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginReq implements Serializable {
    @SerializedName("data")
    public  data data ;
    public  class data{
        @SerializedName("credential")
        public String credential ;
        @SerializedName("key")
        public String key ;

        public data(String credential, String key) {
            this.credential = credential;
            this.key = key;
        }
    }
    @SerializedName("request")
    public request request ;
    public class request
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

    public LoginReq(String credential, String key) {
        this.data = new data(credential,key) ;
        this.request = new request("a7ea23df-7468-439d-9b12-26eb4a760901","1667200102200");
    }
}
