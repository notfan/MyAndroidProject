package com.example.xiwen.myapplication;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jinilei on 2016/5/12.
 */
public class AndroidReceiver2 extends BroadcastReceiver {
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        DeleteNotification();
    }

    private void DeleteNotification() {

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(AndroidReceiver1.NOTIFICATION_ID);

    }
}
