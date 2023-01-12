package com.example.project_a.API.Res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetKey implements Serializable {
            @SerializedName("response")
            public response response ;
            @SerializedName("data")
            public data data  ;
            public static class data{
                @SerializedName("key")
                public String key ;
            }

            public static class response{
                @SerializedName("responseId")
                public String responseId ;
                @SerializedName("responseCode")
                public String responseCode ;
                @SerializedName("responseMessage")
                public String responseMessage ;
                @SerializedName("responseTime")
                public String responseTime ;

                @Override
                public String toString() {
                    return "response{" +
                            "responseId='" + responseId + '\'' +
                            ", responseCode='" + responseCode + '\'' +
                            ", responseMessage='" + responseMessage + '\'' +
                            ", responseTime='" + responseTime + '\'' +
                            '}';
                }
            }

    @Override
    public String toString() {
        return "GetKey{" +
                "response=" + response +
                ", data=" + data +
                '}';
    }
}
