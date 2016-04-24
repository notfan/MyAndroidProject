package com.example.xiwen.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListView1Activity extends AppCompatActivity {
    ListView listView;
    private String[] data = { "Android应用专业开发社区:eoeAndroid.com", "eoeAndroid出品软件如下:",
            "eoeInstaller", "eoeDouban", "eoeWhere",
            "eoeInfoAssistant", "eoeDakarGame","eoeTrack" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data));
        setContentView(listView);
    }

}
