package com.example.project_a.API.Res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class database_postGameUBuy implements Serializable {
    @SerializedName("success")
    public boolean success ;
    @SerializedName("message")
    public String message ;
}
