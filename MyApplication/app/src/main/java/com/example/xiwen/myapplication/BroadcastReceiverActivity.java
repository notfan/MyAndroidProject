package com.example.xiwen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class BroadcastReceiverActivity extends AppCompatActivity {
    public static final int ITEM0 = Menu.FIRST;
    public static final int ITEM1 = Menu.FIRST + 1;

    static final String ACTION_1 = "com.example.xiwen.myapplication.action.NEW_BROADCAST_1";
    static final String ACTION_2 = "com.example.xiwen.myapplication.action.NEW_BROADCAST_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, ITEM0, 0, "显示Notification");
        menu.add(0, ITEM1, 0, "清除Notification");
        menu.findItem(ITEM1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ITEM0:
                actionClickMenuItem1();
                break;
            case ITEM1:
                actionClickMenuItem2();
                break;

        }
        return true;
    }

    private void actionClickMenuItem1() {
        Intent intent = new Intent(ACTION_1);
        sendBroadcast(intent);
    }


    private void actionClickMenuItem2() {
        Intent intent = new Intent(ACTION_2);
        sendBroadcast(intent);

    }
}
