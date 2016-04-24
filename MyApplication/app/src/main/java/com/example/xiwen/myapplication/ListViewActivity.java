package com.example.xiwen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void testListView1(View view) {
        Intent intent = new Intent(this,ListView1Activity.class);
        startActivity(intent);
    }

    public void testListView2(View view) {
        Intent intent = new Intent(this,ListView2Activity.class);
        startActivity(intent);
    }

    public void testListView3(View view) {
        Intent intent = new Intent(this,ListView3Activity.class);
        startActivity(intent);
    }

    public void testListView4(View view) {
        Intent intent = new Intent(this,ListView4Activity.class);
        startActivity(intent);
    }

}
