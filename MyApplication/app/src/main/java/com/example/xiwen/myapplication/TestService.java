package com.example.xiwen.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class TestService extends Service{
    private static final String TAG = "TestService";
    private NotificationManager _nm;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "============> TestService.onBind");
        return null;
    }

    public class LocalBinder extends Binder {
        TestService getService() {
            return TestService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "============> TestService.onUnbind");
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "============> TestService.onRebind");
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "============> TestService.onCreate");
        _nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "============> TestService.onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        _nm.cancel(R.string.service_started);
        Log.e(TAG, "============> TestService.onDestroy");
    }

    private void showNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Test Service")
                .setContentText("Service started")
                .setTicker("Service started")
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setOngoing(false)//true，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                .setSmallIcon(R.drawable.face_1);

        Intent resultIntent = new Intent(this, ServiceHolderActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, 0);
        mBuilder.setContentIntent(pendingIntent);
        _nm.notify(R.string.service_started, mBuilder.build());
    }
}
