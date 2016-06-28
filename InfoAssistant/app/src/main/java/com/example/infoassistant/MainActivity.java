package com.example.infoassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private static final String TAG = "InfoAssistant";
    ListView itemlist = null;
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemlist = (ListView) findViewById(R.id.itemlist);
        refreshListItems();
    }

    private void refreshListItems() {
        list = buildListForSimpleAdapter();
        SimpleAdapter notes = new SimpleAdapter(this, list, R.layout.item_row,
                new String[] { "name", "desc", "img" }, new int[] { R.id.name,
                R.id.desc, R.id.img });
        itemlist.setAdapter(notes);
        itemlist.setOnItemClickListener(this);
        itemlist.setSelection(0);
    }

    private List<Map<String, Object>> buildListForSimpleAdapter() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(3);
        // Build a map for the attributes
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "系统信息");
        map.put("desc", "查看设备系统版本,运营商及其系统信息.");
        map.put("img", R.drawable.system);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "硬件信息");
        map.put("desc", "查看包括CPU,硬盘,内存等硬件信息.");
        map.put("img", R.drawable.hardware);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "软件信息");
        map.put("desc", "查看已经安装的软件信息.");
        map.put("img", R.drawable.software);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "运行时信息");
        map.put("desc", "查看设备运行时的信息.");
        map.put("img", R.drawable.running);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "文件浏览器");
        map.put("desc", "浏览查看文件系统.");
        map.put("img", R.drawable.file_explorer);
        list.add(map);

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent();
        Log.i(TAG, "item clicked! [" + position + "]");
        switch (position) {
            case 0:
                intent.setClass(this, SystemActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent.setClass(this, Hardware.class);
                startActivity(intent);
                break;
            case 2:
                intent.setClass(this, Software.class);
                startActivity(intent);
                break;
            case 3:
                intent.setClass(this, Runing.class);
                startActivity(intent);
                break;
            case 4:
                intent.setClass(this, FSExplorer.class);
                startActivity(intent);
                break;
        }
    }
}
