package com.example.xiwen.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by jinilei on 2016/5/12.
 */
public class AndroidReceiver1 extends BroadcastReceiver {
    Context context;
    public static int NOTIFICATION_ID = 21321;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        showNotification();
    }

    private void showNotification() {

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(android.content.Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("notification title")
                .setContentText("notification content")
                .setTicker("在AndroidReceiver1中")
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setOngoing(false)//true，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                .setSmallIcon(R.drawable.default_icon);

        Intent resultIntent = new Intent(context, BroadcastReceiverActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);
        mBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

}
