package com.wor.smartdrugbox;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {
    private static final String TAG = "DbAdapter";

    private static final String DATABASE_NAME = "SmartDrugBox.db";
    private static final int DATABASE_VERSION = 1;

    public class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String tracks_sql = "CREATE TABLE cell_alarm ("
                    + "_id INTEGER primary key autoincrement, "
                    + "position integer, "
                    + "start_date date ,"
                    + "alarm_time time ,"
                    + "user_id integer"
                    + ");";
            Log.i(TAG, tracks_sql);
            db.execSQL(tracks_sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS cell_alarm;");
            onCreate(db);
        }

    }
}
