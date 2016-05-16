package com.example.xiwen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PlayServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startPlay(View view) {
        startService(new Intent("com.example.xiwen.myapplication.PlayService.START_AUDIO_SERVICE").setPackage(this.getPackageName()));
    }

    public void stopPlay(View view) {
        stopService(new Intent("com.example.xiwen.myapplication.PlayService.START_AUDIO_SERVICE").setPackage(this.getPackageName()));
        finish();
    }
}
