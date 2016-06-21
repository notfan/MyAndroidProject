package com.itracks;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.stetho.Stetho;


/**
 * Created by xiwen on 2016/6/13.
 */
public class iTracksApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        Stetho.initializeWithDefaults(this);
    }
}
