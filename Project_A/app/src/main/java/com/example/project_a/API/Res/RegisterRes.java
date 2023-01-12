package com.example.project_a.API.Res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterRes implements Serializable {
    @SerializedName("response")
    public response response ;
    @SerializedName("data")
    public data data  ;
    public static class data{
        @SerializedName("userId")
        public String userId ;
    }

    public static class response {
        @SerializedName("responseId")
        public String responseId;
        @SerializedName("responseCode")
        public String responseCode;
        @SerializedName("responseMessage")
        public String responseMessage;
        @SerializedName("responseTime")
        public String responseTime;
    }

    @Override
    public String toString() {
        return "RegisterRes{" +
                "response=" + response +
                ", data=" + data +
                '}';
    }
}
