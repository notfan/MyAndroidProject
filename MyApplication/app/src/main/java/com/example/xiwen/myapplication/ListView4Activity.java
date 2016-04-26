package com.example.xiwen.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListView4Activity extends AppCompatActivity {
    ListView listView;
    private String[] data = { "eoeInstaller", "eoeDouban", "eoeWhere",
            "eoeInfoAssistant", "eoeDakarGame", "eoeTrack" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView)findViewById(R.id.listView4);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, data));
        listView.setItemsCanFocus(true);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int position, long arg3) {
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(getApplicationContext(), "未选中",
                        Toast.LENGTH_SHORT).show();
            }
        };
        listView.setOnItemSelectedListener(itemSelectedListener);
    }

}
