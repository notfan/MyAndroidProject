package com.itracks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    private static final String TAG = "Setting";
    //定义菜单需要的常量
    private static final int MENU_BACK = Menu.FIRST + 1;

    // 保存个性化设置
    public static final String SETTING_INFOS = "SETTING_Infos";
    public static final String SETTING_GPS = "SETTING_Gps";
    public static final String SETTING_MAP = "SETTING_Map";
    public static final String SETTING_GPS_POSITON = "SETTING_Gps_p";
    public static final String SETTING_MAP_POSITON = "SETTING_Map_p";

    private Button button_setting_submit;
    private Spinner field_setting_gps;
    private Spinner field_setting_map_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle(R.string.menu_setting);
        findViews();
        restorePrefs();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "save setting infos");
        // Save user preferences. We need an Editor object to
        // make changes. All objects are from android.context.Context
        storePrefs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_BACK, 0, R.string.menu_back).setIcon(
                R.drawable.back).setAlphabeticShortcut('E');
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case MENU_BACK:
                finish();
                break;
        }
        return true;
    }

    private void findViews() {
        Log.d(TAG, "find Views");
        button_setting_submit = (Button) findViewById(R.id.setting_submit);
        field_setting_gps = (Spinner) findViewById(R.id.setting_gps);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.gps, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        field_setting_gps.setAdapter(adapter);

        field_setting_map_level = (Spinner) findViewById(R.id.setting_map_level);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.map, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        field_setting_map_level.setAdapter(adapter2);
    }

    public void setting_submit(View view) {
        Log.d(TAG, "onClick new_track..");
        try {
            String gps = (field_setting_gps.getSelectedItem().toString());
            String map = (field_setting_map_level.getSelectedItem()
                    .toString());
            if (gps.equals("") || map.equals("")) {
                Toast.makeText(this,
                        getString(R.string.setting_null),
                        Toast.LENGTH_SHORT).show();
            } else {
                //保存设定
                storePrefs();
                Toast.makeText(this,
                        getString(R.string.setting_ok),
                        Toast.LENGTH_SHORT).show();
                //跳转到主界面
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
            }
        } catch (Exception err) {
            Log.e(TAG, "error: " + err.toString());
            Toast.makeText(this, getString(R.string.setting_fail),
                    Toast.LENGTH_SHORT).show();
        }
    }

    // Restore preferences
    private void restorePrefs() {
        SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
        int setting_gps_p = settings.getInt(SETTING_GPS_POSITON, 0);
        int setting_map_level_p = settings.getInt(SETTING_MAP_POSITON, 0);
        Log.d(TAG, "restorePrefs: setting_gps= "+ setting_gps_p + ",setting_map_level=" + setting_map_level_p);

        if (setting_gps_p != 0 && setting_map_level_p != 0) {
            field_setting_gps.setSelection(setting_gps_p);
            field_setting_map_level.setSelection(setting_map_level_p);
            button_setting_submit.requestFocus();
        }else if(setting_gps_p != 0 ){
            field_setting_gps.setSelection(setting_gps_p);
            field_setting_map_level.requestFocus();
        }else if(setting_map_level_p != 0){
            field_setting_map_level.setSelection(setting_map_level_p);
            field_setting_gps.requestFocus();
        }else{
            field_setting_gps.requestFocus();
        }
    }

    //保存个人设置
    private void storePrefs() {
        Log.d(TAG, "storePrefs setting infos");
        SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
        settings.edit()
                .putString(SETTING_GPS, field_setting_gps.getSelectedItem().toString())
                .putString(SETTING_MAP, field_setting_map_level.getSelectedItem().toString())
                .putInt(SETTING_GPS_POSITON, field_setting_gps.getSelectedItemPosition())
                .putInt(SETTING_MAP_POSITON, field_setting_map_level.getSelectedItemPosition())
                .commit();
    }

}
