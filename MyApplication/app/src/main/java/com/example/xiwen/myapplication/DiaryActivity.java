package com.example.xiwen.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DiaryActivity extends AppCompatActivity {
    private static final int ACTIVITY_CREATE = 0;
    private static final int ACTIVITY_EDIT = 1;

    private static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;

    private DiaryDbAdapter mDbHelper;
    private Cursor mDiaryCursor = null;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDbHelper = new DiaryDbAdapter(this);
        mDbHelper.open();
        mListView = (ListView) findViewById(R.id.diary_list);
        mListView.setEmptyView(findViewById(R.id.empty_list));
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Cursor c = mDiaryCursor;
                c.moveToPosition(position);
                Intent i = new Intent(DiaryActivity.this, DiaryEditActivity.class);
                i.putExtra(DiaryDbAdapter.KEY_ROWID, id);
                i.putExtra(DiaryDbAdapter.KEY_TITLE, c.getString(c
                        .getColumnIndexOrThrow(DiaryDbAdapter.KEY_TITLE)));
                i.putExtra(DiaryDbAdapter.KEY_BODY, c.getString(c
                        .getColumnIndexOrThrow(DiaryDbAdapter.KEY_BODY)));
                //c.close();
                startActivityForResult(i, ACTIVITY_EDIT);
            }
        };
        mListView.setOnItemClickListener(listener);
        renderListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.menu_insert);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case INSERT_ID:
                createDiary();
                return true;
            case DELETE_ID:
                mDbHelper.deleteDiary(mListView.getSelectedItemId());
                renderListView();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        renderListView();
    }

    private void renderListView() {
        if(mDiaryCursor != null){
            mDiaryCursor.close();
            mDiaryCursor = null;
        }
        mDiaryCursor = mDbHelper.getAllNotes();
        String[] from = new String[] { DiaryDbAdapter.KEY_TITLE,
                DiaryDbAdapter.KEY_CREATED };
        int[] to = new int[] { R.id.text1, R.id.created };
        SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
                R.layout.diary_row, mDiaryCursor, from, to, 0);
        mListView.setAdapter(notes);
    }

    private void createDiary() {
        //mDiaryCursor.close();
        Intent i = new Intent(this, DiaryEditActivity.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }

}
