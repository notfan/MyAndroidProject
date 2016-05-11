package com.example.xiwen.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class SQLiteActivity extends AppCompatActivity {
    DatabaseHelper mOpenHelper;

    private static final String DATABASE_NAME = "dbForTest.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "diary";
    private static final String TITLE = "title";
    private static final String BODY = "body";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sql = "CREATE TABLE " + TABLE_NAME + " (" + TITLE
                    + " text not null, " + BODY + " text not null " + ");";
            Log.i("haiyang:createDB=", sql);
            db.execSQL(sql);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mOpenHelper = new DatabaseHelper(this);
    }

    /*
     * 重新建立数据表
     */
    private void createTable() {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + TITLE
                + " text not null, " + BODY + " text not null " + ");";
        Log.i("haiyang:createDB=", sql);

        try {
            db.execSQL("DROP TABLE IF EXISTS diary");
            db.execSQL(sql);
            setTitle("数据表成功重建");
        } catch (SQLException e) {
            setTitle("数据表重建错误");
        }
    }

    /*
     * 删除数据表
     */
    private void dropTable() {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String sql = "drop table " + TABLE_NAME;
        try {
            db.execSQL(sql);
            setTitle("数据表成功删除：" + sql);
        } catch (SQLException e) {
            setTitle("数据表删除错误");
        }
    }

    /*
     * 插入两条数据
     */
    private void insertItem() {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String sql1 = "insert into " + TABLE_NAME + " (" + TITLE + ", " + BODY
                + ") values('haiyang', 'android的发展真是迅速啊');";
        String sql2 = "insert into " + TABLE_NAME + " (" + TITLE + ", " + BODY
                + ") values('icesky', 'android的发展真是迅速啊');";
        try {
            Log.i("haiyang:sql1=", sql1);
            Log.i("haiyang:sql2=", sql2);
            db.execSQL(sql1);
            db.execSQL(sql2);
            setTitle("插入两条数据成功");
        } catch (SQLException e) {
            setTitle("插入两条数据失败");
        }
    }

    /*
     * 删除其中的一条数据
     */
    private void deleteItem() {
        try {
            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            db.delete(TABLE_NAME, " title = 'haiyang'", null);
            setTitle("删除title为haiyang的一条记录");
        } catch (SQLException e) {

        }

    }

    /*
     * 在屏幕的title区域显示当前数据表当中的数据的条数。
     */
    private void showItems() {

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String col[] = { TITLE, BODY };
        Cursor cur = db.query(TABLE_NAME, col, null, null, null, null, null);
        Integer num = cur.getCount();
        setTitle(Integer.toString(num) + " 条记录");
    }

    public void testInsert(View view) {
        insertItem();
    }

    public void testDelete(View view) {
        deleteItem();
    }

    public void testQuery(View view) {
        showItems();
    }

    public void testDrop(View view) {
        dropTable();
    }

    public void testRecreate(View view) {
        createTable();
    }

}
