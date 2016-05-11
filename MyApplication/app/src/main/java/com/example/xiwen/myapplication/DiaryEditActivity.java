package com.example.xiwen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DiaryEditActivity extends AppCompatActivity {
    private EditText mTitleText;
    private EditText mBodyText;
    private Long mRowId;
    private DiaryDbAdapter mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDbHelper = new DiaryDbAdapter(this);
        mDbHelper.open();
        mTitleText = (EditText) findViewById(R.id.title);
        mBodyText = (EditText) findViewById(R.id.body);

        mRowId = null;
        // 每一个intent都会带一个Bundle型的extras数据。
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString(DiaryDbAdapter.KEY_TITLE);
            String body = extras.getString(DiaryDbAdapter.KEY_BODY);
            mRowId = extras.getLong(DiaryDbAdapter.KEY_ROWID);

            if (title != null) {
                mTitleText.setText(title);
            }
            if (body != null) {
                mBodyText.setText(body);
            }
        }
    }

    public void confirm(View view) {
        String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();
        if (mRowId != null) {
            mDbHelper.updateDiary(mRowId, title, body);
        } else
            mDbHelper.createDiary(title, body);
        Intent mIntent = new Intent();
        setResult(RESULT_OK, mIntent);
        finish();
    }
}
