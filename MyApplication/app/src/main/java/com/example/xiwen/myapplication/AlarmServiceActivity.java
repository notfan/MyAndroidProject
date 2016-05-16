package com.example.xiwen.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class AlarmServiceActivity extends AppCompatActivity {
    private static AlarmServiceActivity appRef = null;
    boolean k = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appRef = this;
    }

    public static AlarmServiceActivity getApp() {
        return appRef;
    }

    public void btEvent(String data) {
        setTitle(data);
    }

    public void callService(View view) {
        setTitle("Waiting... Alarm=5");
        Intent intent = new Intent(AlarmServiceActivity.this, AlarmReceiver.class);
        PendingIntent p_intent = PendingIntent.getBroadcast(
                AlarmServiceActivity.this, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);
        // Schedule the alarm!
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                p_intent);
    }

    public void exitService(View view) {
        Intent intent = new Intent(AlarmServiceActivity.this, AlarmReceiver.class);
        PendingIntent p_intent = PendingIntent.getBroadcast(
                AlarmServiceActivity.this, 0, intent, 0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(p_intent);
        finish();
    }
}
