package com.example.project_a.Storage;

import android.content.Context;
import android.content.SharedPreferences;

public class CommonUtils {
    private static final String PREF_FILE = "pref_saving";
    private static CommonUtils instance ;
    private CommonUtils()
    {

    }

    public static CommonUtils getInstance() {
        if(getInstance() == null)
            instance = new CommonUtils() ;
        return instance;
    }

    public void savePref(String key, String value)
    {
        SharedPreferences shrd = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE) ;
        shrd.edit().putString(key, value).apply(); ;

    }
    public String getPref(String key)
    {
        SharedPreferences shrd = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE) ;

        return shrd.getString(PREF_FILE,null) ;
    }
    public void clearPref(String key)
    {
        SharedPreferences shrd = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE) ;
        shrd.edit().remove(key).apply(); ;

    }
}
