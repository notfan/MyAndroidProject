package com.example.xiwen.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListView2Activity extends AppCompatActivity {
    private List<Map<String, Object>> data;
    private ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PrepareData();
        listView = (ListView)findViewById(R.id.listView2) ;
//		 SimpleAdapter adapter = new SimpleAdapter(this, data,
//		 android.R.layout.simple_list_item_2, new String[] { "姓名","性别" },
//		 new int[] { android.R.id.text1 , android.R.id.text2});
        // 利用自己的layout来进行显示两项
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.list_item, new String[] { "姓名", "性别" }, new int[] {
						R.id.mview1, R.id.mview2 });
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        listView.setOnItemClickListener(listener);
    }

    private void PrepareData() {
        data = new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        item = new HashMap<String, Object>();
        item.put("姓名", "张三小朋友");
        item.put("性别", "男");
        data.add(item);
        item = new HashMap<String, Object>();
        item.put("姓名", "王五同学");
        item.put("性别", "男");
        data.add(item);
        item = new HashMap<String, Object>();
        item.put("姓名", "小李师傅");
        item.put("性别", "女");
        data.add(item);
    }
}
