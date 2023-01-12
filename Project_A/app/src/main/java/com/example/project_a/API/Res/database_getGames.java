package com.example.project_a.API.Res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class database_getGames implements Serializable {
    @SerializedName("game")
    public List<String> games ;
}
