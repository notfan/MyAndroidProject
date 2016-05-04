package com.example.xiwen.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class SettingActivity extends AppCompatActivity {
    public static final String SETTING_INFOS = "SETTING_Infos";
    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASSWORD";

    private EditText field_name;
    private EditText filed_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        field_name = (EditText) findViewById(R.id.name);
        filed_pass = (EditText) findViewById(R.id.password);
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
        String name = settings.getString(NAME, "");
        String password = settings.getString(PASSWORD, "");
        //Set value
        field_name.setText(name);
        filed_pass.setText(password);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
        settings.edit()
                .putString(NAME, field_name.getText().toString())
                .putString(PASSWORD, filed_pass.getText().toString())
                .commit();
    }
}
