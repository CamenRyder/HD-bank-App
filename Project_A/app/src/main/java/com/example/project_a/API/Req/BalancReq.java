package com.example.project_a.API.Req;

import com.example.project_a.Storage.App;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BalancReq implements Serializable {
                @SerializedName("data")
            public data  data ;
                public static class data
                {
                    @SerializedName("acctNo")
                    public String acctNo;

                    public data(String acctNo) {
                        this.acctNo = acctNo;
                    }
                }
                @SerializedName("request")
                          public request request ;
                public static class request
                        {
                            @SerializedName("requestId")
                            public String requestId ;
                            @SerializedName("requestTime")
                            public String requestTime  ;


                            public request(String requestId, String requestTime) {
                                this.requestId = requestId;
                                this.requestTime = requestTime;
                            }
                        }

    public BalancReq() {
        this.data = new data(App.getInstance().getStorage().accountNo);
        this.request = new request("a7ea23df-7468-439d-9b12-26eb4a760901","1667200102200");

    }
}
