package com.wor.smartdrugbox.ui;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.wor.smartdrugbox.db.DbAdapter;

public class CellAlarmDbAdapter extends DbAdapter {
    private static final String TAG = "CellAlarmDbAdapter";

    public static final String TABLE_NAME = "cell_alarm";

    public static final String ID = "_id";
    public static final String KEY_ROWID = "_id";
    public static final String POSITION = "position";
    public static final String START_DATE = "start_date";
    public static final String ALARM_TIME = "alarm_time";
    public static final String USER_ID = "user_id";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;

    public CellAlarmDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public CellAlarmDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

}
