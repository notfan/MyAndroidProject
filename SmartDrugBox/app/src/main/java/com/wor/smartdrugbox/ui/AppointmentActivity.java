package com.wor.smartdrugbox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wor.smartdrugbox.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemClickListener {
    ListView listView;
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AppointmentSettingActivity.class);
                startActivity(intent);
            }
        });

        //set listview
        listView = (ListView)findViewById(R.id.listView);
        refreshListItems();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appointment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent();

        if (id == R.id.nav_appointment) {
            //intent.setClass(this, AppointmentActivity.class);
        } else if (id == R.id.nav_calendar) {
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact) {
            intent.setClass(this, ContactActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_doctor) {
            intent.setClass(this, DoctorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_help) {
            intent.setClass(this, HelpActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_note) {
            intent.setClass(this, NoteActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_report) {
            intent.setClass(this, ReportActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            intent.setClass(this, SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tip) {
            intent.setClass(this, TipActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void refreshListItems() {
        list = buildListForSimpleAdapter();
        SimpleAdapter bar = new SimpleAdapter(this, list, R.layout.appointment_row,
                new String[] { "appointment_title", "appointment_time", "doctor_img" }, new int[] { R.id.appointment_title,
                R.id.appointment_time, R.id.doctor_img });
        listView.setAdapter(bar);
        listView.setOnItemClickListener(this);
        listView.setSelection(0);
    }

    private List<Map<String, Object>> buildListForSimpleAdapter() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(3);
        // Build a map for the attributes
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appointment_title", "Matthew - 看牙");
        map.put("appointment_time", "周三，3月2日，9:00 AM");
        map.put("doctor_img", R.drawable.doctor_img);
        list.add(map);

        return list;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, AppointmentDetailActivity.class);
        startActivity(intent);
    }
}
