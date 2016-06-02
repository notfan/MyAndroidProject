package com.itracks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewTrackActivity extends AppCompatActivity {
    private static final String TAG = "NewTrack";
    private EditText field_new_name;
    private EditText field_new_desc;

    private TrackDbAdapter mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track);
        setTitle(R.string.menu_new);
        findViews();

        mDbHelper = new TrackDbAdapter(this);
        mDbHelper.open();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        mDbHelper.close();
    }

    private void findViews() {
        Log.d(TAG, "find Views");
        field_new_name = (EditText) findViewById(R.id.new_name);
        field_new_desc = (EditText) findViewById(R.id.new_desc);
    }

    public void new_track(View view) {

        Log.d(TAG, "onClick new_track..");
        try {
            String name = (field_new_name.getText().toString());
            String desc = (field_new_desc.getText()
                    .toString());
            if (name.equals("")) {
                Toast.makeText(this,
                        getString(R.string.new_name_null),
                        Toast.LENGTH_SHORT).show();
            } else {
                // TODO 调用存储接口保存到数据库并启动service
                Long row_id = mDbHelper.createTrack(name, desc);
                Log.d(TAG, "row_id="+row_id);

                Intent intent = new Intent();
                intent.setClass(this, ShowTrackActivity.class);
                intent.putExtra(TrackDbAdapter.KEY_ROWID, row_id);
                intent.putExtra(TrackDbAdapter.NAME, name);
                intent.putExtra(TrackDbAdapter.DESC, desc);

                startActivity(intent);
            }
        } catch (Exception err) {
            Log.e(TAG, "error: " + err.toString());
            Toast.makeText(this, getString(R.string.new_fail),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
