package com.example.xiwen.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity {
    private static int NOTIFICATIONS_ID = R.layout.activity_toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void showShortToast(View view) {
        showToast(Toast.LENGTH_SHORT);
    }

    public void showLongToast(View view) {
        showToast(Toast.LENGTH_LONG);
        showNotification();
    }

    protected void showToast(int type) {

        View view = inflateView(R.layout.toast);

        TextView tv = (TextView) view.findViewById(R.id.content);
        tv.setText("加入专业Android开发社区eoeAndroid.com，让你的应用开发能力迅速提高");

        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setDuration(type);
        toast.show();
    }

    private View inflateView(int resource) {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return vi.inflate(resource, null);
    }

    protected void showNotification() {

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        CharSequence title = "最专业的Android应用开发社区";
        CharSequence contents = "eoeandroid.com";

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, ToastAndNotificationActivity.class), 0);
        mBuilder.setContentTitle(title)
                .setContentText(contents)
                .setContentIntent(contentIntent)
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setOngoing(false)//true，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                .setSmallIcon(R.drawable.default_icon);

        Notification notification = mBuilder.build();
        // 100ms延迟后，振动250ms，停止100ms后振动500ms
        notification.vibrate = new long[] { 100, 250, 500, 500 };

        notificationManager.notify(NOTIFICATIONS_ID, notification);
    }

}
