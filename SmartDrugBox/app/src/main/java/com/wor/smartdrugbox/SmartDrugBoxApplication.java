package com.wor.smartdrugbox;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by xiwen on 2016/9/23.
 */
public class SmartDrugBoxApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
