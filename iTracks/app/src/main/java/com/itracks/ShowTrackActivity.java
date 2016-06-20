package com.itracks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;

public class ShowTrackActivity extends AppCompatActivity {
    private static final int MENU_NEW = Menu.FIRST + 1;
    private static final int MENU_CON = MENU_NEW + 1;
    private static final int MENU_DEL = MENU_CON + 1;
    private static final int MENU_MAIN = MENU_DEL + 1;

    private TrackDbAdapter mDbHelper;
    private LocateDbAdapter mlcDbHelper;

    private static final String TAG = "ShowTrack";
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private boolean bShowTraffic = false;

    LocationClient mLocClient;
    private BDLocationListener locationListener;

    private int track_id;
    private Long rowId;

    boolean isFirstLoc = true; // 是否首次定位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_track);
        findViews();
        revArgs();
        //paintLocates();
        startTrackService();
    }

    private void startTrackService() {
        Intent i = new Intent("com.iTracks.START_TRACK_SERVICE");
        i.putExtra(LocateDbAdapter.TRACKID, track_id);
        startService(i.setPackage(this.getPackageName()));
    }

    private void stopTrackService() {
        stopService(new Intent("com.iTracks.START_TRACK_SERVICE").setPackage(this.getPackageName()));
    }

    private void findViews() {
        Log.d(TAG, "find Views");
        // Get the map view from resource file
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        SharedPreferences settings = getSharedPreferences(SettingActivity.SETTING_INFOS, 0);
        String setting_gps = settings.getString(SettingActivity.SETTING_MAP, "10");
        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(Float.parseFloat(setting_gps));
        mBaiduMap.animateMapStatus(u);

        // 开启定位图层

        mBaiduMap.setMyLocationEnabled(true);
        mLocClient = new LocationClient(this);
        locationListener = new MyLocationListener();
        mLocClient.registerLocationListener(locationListener);

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();


    }

    public void toggleNormal(View view) {
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }

    public void toggleSatellite(View view) {
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
    }

    public void toggleTraffic(View view) {
        mBaiduMap.setTrafficEnabled(bShowTraffic = !bShowTraffic);
    }

    public void centerOnGPSPosition(View view) {
        Log.d(TAG, "centerOnGPSPosition");

        BDLocation location = mLocClient.getLastKnownLocation();
        LatLng ll = new LatLng(location.getLatitude(),
                location.getLongitude());
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    private void revArgs() {
        Log.d(TAG, "revArgs.");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString(TrackDbAdapter.NAME);
            //String desc = extras.getString(TrackDbAdapter.DESC);
            rowId = extras.getLong(TrackDbAdapter.KEY_ROWID);
            track_id = rowId.intValue();
            Log.d(TAG, "rowId=" + rowId);
            if (name != null) {
                setTitle(name);
            }
        }
    }

    protected class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation loc) {
            Log.d(TAG, "MyLocationListener::onReceiveLocation..");
            if (loc != null) {
                Toast.makeText(
                        getBaseContext(),
                        "Location changed : Lat: " + loc.getLatitude()
                                + " Lng: " + loc.getLongitude(),
                        Toast.LENGTH_SHORT).show();
                // Set up the overlay controller
                // mOverlayController = mMapView.createOverlayController();
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(loc.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(loc.getLatitude())
                        .longitude(loc.getLongitude()).build();
                mBaiduMap.setMyLocationData(locData);
                if (isFirstLoc) {
                    isFirstLoc = false;
                    LatLng ll = new LatLng(loc.getLatitude(),
                            loc.getLongitude());
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(ll).zoom(18.0f);
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                }
                // //////////
                //if(mlcDbHelper == null){
                //	mlcDbHelper.open();
                //}
                //mlcDbHelper.createLocate(track_id,  loc.getLongitude(),loc.getLatitude(), loc.getAltitude());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_CON, 0, R.string.menu_con).setIcon(
                R.drawable.con_track).setAlphabeticShortcut('C');
        menu.add(0, MENU_DEL, 0, R.string.menu_del).setIcon(R.drawable.delete)
                .setAlphabeticShortcut('D');
        menu.add(0, MENU_NEW, 0, R.string.menu_new).setIcon(
                R.drawable.new_track).setAlphabeticShortcut('N');
        menu.add(0, MENU_MAIN, 0, R.string.menu_main).setIcon(R.drawable.icon)
                .setAlphabeticShortcut('M');
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case MENU_NEW:
                intent.setClass(this, NewTrackActivity.class);
                startActivity(intent);
                return true;
            case MENU_CON:
                // TODO: 继续跟踪选择的记录
                startTrackService();
                return true;
            case MENU_DEL:
                mDbHelper = new TrackDbAdapter(this);
                mDbHelper.open();
                if (mDbHelper.deleteTrack(rowId)) {
                    mDbHelper.close();
                    intent.setClass(this, MainActivity.class);
                    startActivity(intent);
                }else{
                    mDbHelper.close();
                }
                return true;
            case MENU_MAIN:
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
        stopTrackService();
    }
}
