package com.example.project_a.API.Res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class database_getIn4 implements Serializable {
    @SerializedName("username")
    public String username ;
    @SerializedName("pass")
    public String pass ;
    @SerializedName("name")
    public String name ;
    @SerializedName("accNo")
    public String accNo ;
    @SerializedName("identity")
    public String identity ;
    @SerializedName("phone")
    public String phone ;
    @SerializedName("gmail")
    public String gmail ;

}
