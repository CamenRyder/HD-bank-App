package com.example.project_a.Storage;

import android.app.Application;

public class App extends Application {
    private static App instance ;
        private Storage storage ;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this  ;
        storage = new Storage() ;
    }

    public Storage getStorage() {
        return storage;
    }

    public static App getInstance(){
        return instance ;
    }

}
