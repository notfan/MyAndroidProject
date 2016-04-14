package com.example.xiwen.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckBoxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void getCheckBox(View view) {
        String r = "";
        CheckBox plainCB = (CheckBox) findViewById(R.id.plain_cb);
        CheckBox serifCB = (CheckBox) findViewById(R.id.serif_cb);
        CheckBox boldCB = (CheckBox) findViewById(R.id.bold_cb);
        CheckBox italicCB = (CheckBox) findViewById(R.id.italic_cb);
        if(plainCB.isChecked()){
            r = r + "," + plainCB.getText();
        }
        if(serifCB.isChecked()){
            r = r + "," + serifCB.getText();
        }
        if(boldCB.isChecked()){
            r = r + "," + boldCB.getText();
        }
        if(italicCB.isChecked()){
            r = r + "," + italicCB.getText();
        }
        TextView textView = (TextView) findViewById(R.id.cb_status);
        textView.setText("Checked: " + r);
    }

}
