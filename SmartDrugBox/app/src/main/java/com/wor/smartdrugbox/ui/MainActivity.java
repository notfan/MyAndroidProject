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

import com.novaapps.floatingactionmenu.FloatingActionMenu;
import com.wor.smartdrugbox.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemClickListener,FloatingActionMenu.OnMenuItemClickListener {
    private CellAlarmDbAdapter mDbHelper;
    ListView listView;
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_avatar);
        toolbar.setTitle(R.string.nick_name);
        setSupportActionBar(toolbar);

        //set floating menu
        FloatingActionMenu menu = (FloatingActionMenu) findViewById(R.id.fab_menu_line);
        menu.setOnMenuItemClickListener(this);

        //init data
        mDbHelper = new CellAlarmDbAdapter(this);
        mDbHelper.open();

        //set listview
        listView = (ListView)findViewById(R.id.listView);
        refreshListItems();

        //set drawer
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
    protected void onStop() {
        super.onStop();
        mDbHelper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intent.setClass(this, TipActivity.class);
            startActivity(intent);
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
            intent.setClass(this, AppointmentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_calendar) {
            //intent.setClass(this, MainActivity.class);
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

    public void switchView(View view) {
        Intent intent = new Intent(this,WeekViewActivity.class);
        startActivity(intent);
    }

    private void refreshListItems() {
        list = buildListForSimpleAdapter();
        SimpleAdapter bar = new SimpleAdapter(this, list, R.layout.cell_row,
                new String[] { "drug_name1", "desc1", "drug_name2", "desc2", "alarm_time", "cell_tip", "cell_img" }, new int[] { R.id.drug_name1,
                R.id.desc1, R.id.drug_name2, R.id.desc2, R.id.alarm_time, R.id.cell_tip, R.id.cell_img });
        listView.setAdapter(bar);
        listView.setOnItemClickListener(this);
        listView.setSelection(0);
    }

    private List<Map<String, Object>> buildListForSimpleAdapter() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(3);
        // Build a map for the attributes
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("drug_name1", "药片1 1");
        map.put("desc1", "早饭前服用");
        map.put("alarm_time", "8:00 AM");
        map.put("cell_tip", R.drawable.ic_taked);
        map.put("cell_img", R.drawable.cell);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("drug_name1", "药片2 1");
        map.put("desc1", "嚼服");
        map.put("alarm_time", "10:00 AM");
        map.put("cell_tip", R.drawable.ic_not_taked);
        map.put("cell_img", R.drawable.cell);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("drug_name1", "胶囊1 2");
        map.put("desc1", "午饭前服用");
        map.put("alarm_time", "12:00 AM");
        map.put("cell_tip", R.drawable.ic_alarm);
        map.put("cell_img", R.drawable.cell);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("drug_name1", "药丸1 1");
        map.put("alarm_time", "2:00 PM");
        map.put("cell_tip", R.drawable.ic_alarm);
        map.put("cell_img", R.drawable.cell);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("drug_name1", "药丸2 1");
        map.put("alarm_time", "4:00 PM");
        map.put("cell_tip", R.drawable.ic_alarm);
        map.put("cell_img", R.drawable.cell);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("drug_name1", "药片3 1");
        map.put("desc1", "晚饭后服用");
        map.put("alarm_time", "6:00 PM");
        map.put("cell_tip", R.drawable.ic_alarm);
        map.put("cell_img", R.drawable.cell);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("drug_name1", "药片4 1");
        map.put("alarm_time", "8:00 PM");
        map.put("cell_tip", R.drawable.ic_alarm);
        map.put("cell_img", R.drawable.cell);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("drug_name1", "未设置");
        map.put("cell_img", R.drawable.cell);
        list.add(map);

        return list;
    }
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, CellDetailActivity.class);
        startActivity(intent);
    }
    public void onMenuItemClick(FloatingActionMenu fam, int index, FloatingActionButton item) {
        int id = item.getId();
        Intent intent = new Intent();

        if (id == R.id.fab_drug) {
            intent.setClass(this, DrugSettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.fab_appointment) {
            intent.setClass(this, AppointmentSettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.fab_note) {
            intent.setClass(this, NoteSettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.fab_contact) {

        }

    }
}
