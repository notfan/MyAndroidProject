package com.example.xiwen.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {
    Spinner spinner_c;
    Spinner spinner_2;
    private ArrayAdapter<String> aspnCountries;
    private List<String> allcountries;

    private static final String[] mCountries = { "China" ,"Russia", "Germany",
            "Ukraine", "Belarus", "USA" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_c = (Spinner) findViewById(R.id.spinner_1);
        allcountries = new ArrayList<String>();
        for (int i = 0; i < mCountries.length; i++) {
            allcountries.add(mCountries[i]);
        }
        aspnCountries = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, allcountries);
        aspnCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_c.setAdapter(aspnCountries);

    }

}
