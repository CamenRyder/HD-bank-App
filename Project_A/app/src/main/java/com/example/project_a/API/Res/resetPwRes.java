package com.example.project_a.API.Res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class resetPwRes implements Serializable
{
        @SerializedName("response")
    public response response ;
        public static class response
        {
            @SerializedName("responseId")
            public String responseId ;
            @SerializedName("responseCode")
            public String responseCode ;
            @SerializedName("responseMessage")
            public String responseMessage ;
            @SerializedName("responseTime")
            public String responseTime ;
        }

}
