package com.example.xiwen.myapplication;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.xiwen.myapplication.Diary.DiaryColumns;

public class Diary2Activity extends AppCompatActivity {
    // 插入一条新纪录
    public static final int MENU_ITEM_INSERT = Menu.FIRST;
    // 编辑内容
    public static final int MENU_ITEM_EDIT = Menu.FIRST + 1;
    public static final int MENU_ITEM_DELETE = Menu.FIRST + 2;

    private static final String[] PROJECTION = new String[] { DiaryColumns._ID,
            DiaryColumns.TITLE, DiaryColumns.CREATED };
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.getData() == null) {
            intent.setData(DiaryColumns.CONTENT_URI);
        }
        mListView = (ListView) findViewById(R.id.diary_list);
        mListView.setEmptyView(findViewById(R.id.empty_list));
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);
                startActivity(new Intent(Diary2EditActivity.EDIT_DIARY_ACTION, uri));
            }
        };
        mListView.setOnItemClickListener(listener);
        renderListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_ITEM_INSERT, 0, R.string.menu_insert);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        final boolean haveItems = mListView.getAdapter().getCount() > 0;
        if (haveItems) {
            // 如果选中一个Item的话
            if (mListView.getSelectedItemId() > 0) {
                menu.removeGroup(1);
                Uri uri = ContentUris.withAppendedId(getIntent().getData(),
                        mListView.getSelectedItemId());
                Intent intent = new Intent(null, uri);
                menu.add(1, MENU_ITEM_EDIT, 1, "编辑内容").setIntent(intent);
                menu.add(1, MENU_ITEM_DELETE, 1, "删除当前日记");
            }

        }else{
            menu.removeGroup(1);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 插入一条数据
            case MENU_ITEM_INSERT:
                Intent intent0 = new Intent(this, Diary2EditActivity.class);
                intent0.setAction(Diary2EditActivity.INSERT_DIARY_ACTION);
                intent0.setData(getIntent().getData());
                startActivity(intent0);
                return true;
            // 编辑当前数据内容
            case MENU_ITEM_EDIT:
                Intent intent = new Intent(this, Diary2EditActivity.class);
                intent.setData(item.getIntent().getData());
                intent.setAction(Diary2EditActivity.EDIT_DIARY_ACTION);
                startActivity(intent);
                return true;
            // 删除当前数据
            case MENU_ITEM_DELETE:
                Uri uri = ContentUris.withAppendedId(getIntent().getData(),
                        mListView.getSelectedItemId());
                getContentResolver().delete(uri, null, null);
                renderListView();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //renderListView();
    }

    private void renderListView() {
        Cursor cursor = getContentResolver().query(getIntent().getData(), PROJECTION, null,
                null, DiaryColumns.DEFAULT_SORT_ORDER);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.diary_row, cursor, new String[] { DiaryColumns.TITLE,
                DiaryColumns.CREATED }, new int[] { R.id.text1,
                R.id.created }, 0);
        mListView.setAdapter(adapter);
    }
}
