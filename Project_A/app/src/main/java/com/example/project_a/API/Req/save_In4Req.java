package com.example.project_a.API.Req;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class save_In4Req implements Serializable {

    @SerializedName("username")
    public String userName ;
    @SerializedName("pass")
    public String pass ;
    @SerializedName("name")
    public String name ;
    @SerializedName("accNo")
    public String acctNo ;
    @SerializedName("identity")
    public String identity ;
    @SerializedName("phone")
    public String phone ;
    @SerializedName("gmail")
    public String gmail ;

    public save_In4Req(String gmail, String userName, String pass, String phone, String identity, String acctNo, String name) {
        this.gmail = gmail;
        this.userName = userName;
        this.pass = pass;
        this.phone = phone;
        this.identity = identity;
        this.acctNo = acctNo;
        this.name = name;
    }
}
