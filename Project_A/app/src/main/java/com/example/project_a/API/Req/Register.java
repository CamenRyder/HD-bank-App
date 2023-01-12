package com.example.project_a.API.Req;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Register implements Serializable {
    @SerializedName("request")
    public request request ;
    @SerializedName("data")
    public data data ;

            public static class data{
                @SerializedName("credential")
                public String credential ;
                @SerializedName("email")
                public String email ;
                @SerializedName("fullName")
                public String fullName ;
                @SerializedName("identityNumber")
                public String identityNumber ;
                @SerializedName("key")
                public String key ;
                @SerializedName("phone")
                public String phone ;

                public data(String credential, String email, String fullName, String identityNumber, String key, String phone)
                {
                    this.credential = credential;
                    this.email = email;
                    this.fullName = fullName;
                    this.identityNumber = identityNumber;
                    this.key = key;
                    this.phone = phone;
                }
            }

    public Register(String credential,String email,String fullName,String identityNumber,String key ,String phone  ) {
        data = new data(credential,email,fullName,identityNumber,key,phone) ;
        request  = new request("a7ea23df-7468-439d-9b12-26eb4a76090","1667200102200") ;
    }

    public static class request{
            @SerializedName("requestId")
        public String requestId ;
            @SerializedName("requestTime")
        public String requestTime  ;

        public request(String requestId, String requestTime) {
            this.requestId = requestId;
            this.requestTime = requestTime;
        }
    }

}
