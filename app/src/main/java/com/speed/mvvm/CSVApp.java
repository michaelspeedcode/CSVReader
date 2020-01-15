package com.speed.mvvm;

import android.app.Application;

public class CSVApp extends Application {

    private static CSVApp sInstance;

    public static CSVApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

}
