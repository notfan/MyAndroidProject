package com.example.xiwen.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class NotifyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        AlarmServiceActivity app = AlarmServiceActivity.getApp();
        app.btEvent("from NotifyService");
        Toast.makeText(this,"hello!",Toast.LENGTH_LONG).show();
    }
}
