package com.example.xiwen.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class NotificationActivity extends AppCompatActivity {
    private static int NOTIFICATIONS_ID = R.layout.activity_notification;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    public void testNotificationSun(View view) {
    }

    private void setWeather(String tickerText, String title, String content,
                            int drawable) {

        Notification notification = new Notification(drawable, tickerText,
                System.currentTimeMillis());

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, ToastAndNotificationActivity.class), 0);

//        notification.setLatestEventInfo(this, title, content, contentIntent);

        mNotificationManager.notify(NOTIFICATIONS_ID, notification);
    }

}
