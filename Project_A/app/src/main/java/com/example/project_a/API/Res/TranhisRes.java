package com.example.project_a.API.Res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TranhisRes implements Serializable{
    @SerializedName("response")
    public response response ;
    @SerializedName("data")
    public data data ;
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

    public static class data implements  Serializable
    {
        @SerializedName("transHis")
        public List<transHis> transHis ;

    }

    public static class transHis implements Serializable{
        @SerializedName("transDesc")
        public String transDesc ;
        @SerializedName("transDate")
        public String transDate ;
        @SerializedName("transAmount")
        public String transAmount ;
    }

}
