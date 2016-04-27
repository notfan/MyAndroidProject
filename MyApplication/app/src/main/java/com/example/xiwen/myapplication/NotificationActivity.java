package com.example.xiwen.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class NotificationActivity extends AppCompatActivity {
    private static int NOTIFICATIONS_ID = R.layout.activity_notification;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("notification title")
                .setContentText("notification content")
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
                .setTicker("notification ticker")
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setOngoing(false)//true，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                .setSmallIcon(R.drawable.default_icon);
    }

    public void testNotificationSun(View view) {
        setWeather("晴空万里", "天气预报", "晴空万里", R.drawable.sun);
    }

    public void testNotificationCloudy(View view) {
        setWeather("阴云密布", "天气预报", "阴云密布", R.drawable.cloudy);
    }

    public void testNotificationRain(View view) {
        setWeather("大雨连绵", "天气预报", "大雨连绵", R.drawable.rain);
    }

    public void testNotificationSound(View view) {
        setDefault(Notification.DEFAULT_SOUND);
    }

    public void testNotificationVibrate(View view) {
        setDefault(Notification.DEFAULT_VIBRATE);
    }

    public void testNotificationLights(View view) {
        setDefault(Notification.DEFAULT_LIGHTS);
    }
    public void testNotificationAll(View view) {
        setDefault(Notification.DEFAULT_ALL);
    }

    private void setWeather(String tickerText, String title, String content,
                            int drawable) {
        mBuilder.setContentTitle(title)
                .setContentText(content)
                .setTicker(tickerText)
                .setSmallIcon(drawable);
        Intent resultIntent = new Intent(this, ToastAndNotificationActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(NOTIFICATIONS_ID, mBuilder.build());
    }

    private void setDefault(int defaults) {
        String title = "天气预报";
        String content = "晴空万里";

        mBuilder.setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.sun);

        Intent resultIntent = new Intent(this, ToastAndNotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, 0);
        mBuilder.setContentIntent(pendingIntent);
        Notification mNotification = mBuilder.build();
        mNotification.defaults = defaults;
        if(defaults == Notification.DEFAULT_LIGHTS) {
            mNotification.flags = Notification.FLAG_SHOW_LIGHTS;
        }
        mNotificationManager.notify(NOTIFICATIONS_ID,mNotification);
    }

    public PendingIntent getDefalutIntent(int flags){
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(), flags);
        return pendingIntent;
    }
}
