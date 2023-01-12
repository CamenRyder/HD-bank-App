package com.example.project_a.API.Req;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TranhisReq implements Serializable {
    @SerializedName("data")
    public data data ;
    @SerializedName("request")
    public request request ;



    public TranhisReq(String accNo,String fromDate,String toDate ) {
        data = new data(accNo,fromDate,toDate) ;
        request  = new request("a7ea23df-7468-439d-9b12-26eb4a760901","1667200102200") ;
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


    public static class data implements Serializable{
        @SerializedName("acctNo")
        public String acctNo ;
        @SerializedName("fromDate")
        public String fromDate ;
        @SerializedName("toDate")
        public String toDate ;

        public data(String acctNo, String fromDate, String toDate) {
            this.acctNo = acctNo;
            this.fromDate = fromDate;
            this.toDate = toDate;
        }
    }
}
