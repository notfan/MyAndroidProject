package com.example.xiwen.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

public class ListView3Activity extends ListActivity {
    private String[] data ={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list_view3);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data));
    }

}
