package com.example.xiwen.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class ServiceHolderActivity extends AppCompatActivity {
    private boolean _isBound;
    private TestService _boundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_holder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private ServiceConnection _connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            _boundService = ((TestService.LocalBinder)service).getService();

            Toast.makeText(ServiceHolderActivity.this, "Service connected",
                    Toast.LENGTH_SHORT).show();
        }

        public void onServiceDisconnected(ComponentName className) {
            // unexpectedly disconnected,we should never see this happen.
            _boundService = null;
            Toast.makeText(ServiceHolderActivity.this, "Service disconnected",
                    Toast.LENGTH_SHORT).show();
        }
    };

    public void startTestService(View view) {
        Intent i = new Intent(this, TestService.class);
        this.startService(i);
    }

    public void stopTestService(View view) {
        Intent i = new Intent(this, TestService.class);
        this.stopService(i);
    }

    public void bindTestService(View view) {
        Intent i = new Intent(this, TestService.class);
        bindService(i, _connection, Context.BIND_AUTO_CREATE);
        _isBound = true;
    }

    public void unbindTestService(View view) {
        if (_isBound) {
            unbindService(_connection);
            _isBound = false;
        }
    }

}
