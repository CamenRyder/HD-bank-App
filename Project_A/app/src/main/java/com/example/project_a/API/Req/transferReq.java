package com.example.project_a.API.Req;

import com.example.project_a.Storage.App;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class transferReq implements Serializable {
    @SerializedName("data")
    public data data ;
    public static class data
    {
        @SerializedName("amount")
        public String amount ;
        @SerializedName("description")
        public String description ;
        @SerializedName("fromAcct")
        public String fromAcct ;
        @SerializedName("toAcct")
        public String toAcct ;

        public data(String amount, String description, String fromAcct, String toAcct) {
            this.amount = amount;
            this.description = description;
            this.fromAcct = fromAcct;
            this.toAcct = toAcct;
        }
    }

    @SerializedName("request")
    public request request ;
    public static  class request
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

    public transferReq(String amount, String Desc, String toAcct) {
        this.data = new data(amount,Desc, App.getInstance().getStorage().accountNo,toAcct);
        this.request = new request("a7ea23df-7468-439d-9b12-26eb4a760901","1667200102200");
    }
}
