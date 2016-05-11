package com.example.xiwen.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.xiwen.myapplication.Diary.DiaryColumns;

public class Diary2EditActivity extends AppCompatActivity {
    private static final String TAG = "Diary";
    public static final String EDIT_DIARY_ACTION = "com.example.xiwen.myapplication.ActivityDiaryEditor.EDIT_DIARY";
    public static final String INSERT_DIARY_ACTION = "com.example.xiwen.myapplication.ActivityDiaryEditor.action.INSERT_DIARY";

    /**
     * 查询cursor时候，感兴趣的那些条例。
     */
    private static final String[] PROJECTION = new String[] { DiaryColumns._ID, // 0
            DiaryColumns.TITLE, DiaryColumns.BODY, // 1
    };

    private static final int STATE_EDIT = 0;
    private static final int STATE_INSERT = 1;

    private int mState;

    private Uri mUri;
    private Cursor mCursor;

    private EditText mTitleText;
    private EditText mBodyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        final String action = intent.getAction();
        mTitleText = (EditText) findViewById(R.id.title);
        mBodyText = (EditText) findViewById(R.id.body);

        if (EDIT_DIARY_ACTION.equals(action)) {// 编辑日记
            mState = STATE_EDIT;
            mUri = intent.getData();
            mCursor = getContentResolver().query(mUri, PROJECTION, null, null, null);
            mCursor.moveToFirst();
            String title = mCursor.getString(1);
            mTitleText.setTextKeepState(title);
            String body = mCursor.getString(2);
            mBodyText.setTextKeepState(body);
            setResult(RESULT_OK, (new Intent()).setAction(mUri.toString()));
            setTitle("编辑日记");
        } else if (INSERT_DIARY_ACTION.equals(action)) {// 新建日记
            mState = STATE_INSERT;
            setTitle("新建日记");
        } else {
            Log.e(TAG, "no such action error");
            finish();
            return;
        }
    }

    public void confirm(View view) {
        if (mState == STATE_INSERT) {
            insertDiary();
        } else {
            updateDiary();
        }
        Intent mIntent = new Intent();
        setResult(RESULT_OK, mIntent);
        finish();
    }

    private void insertDiary() {
        String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();
        ContentValues values = new ContentValues();
        values.put(Diary.DiaryColumns.CREATED, DiaryContentProvider
                .getFormateCreatedDate());
        values.put(Diary.DiaryColumns.TITLE, title);
        values.put(Diary.DiaryColumns.BODY, body);
        getContentResolver().insert(Diary.DiaryColumns.CONTENT_URI, values);

    }

    private void updateDiary() {
        String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();
        ContentValues values = new ContentValues();
        values.put(Diary.DiaryColumns.CREATED, DiaryContentProvider
                .getFormateCreatedDate());
        values.put(Diary.DiaryColumns.TITLE, title);
        values.put(Diary.DiaryColumns.BODY, body);
        getContentResolver().update(mUri, values,
                null, null);

    }
}
