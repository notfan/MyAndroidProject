package com.itracks;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

public class ShowTrackActivity extends AppCompatActivity {
    private static final int MENU_NEW = Menu.FIRST + 1;
    private static final int MENU_CON = MENU_NEW + 1;
    private static final int MENU_DEL = MENU_CON + 1;
    private static final int MENU_MAIN = MENU_DEL + 1;

    private TrackDbAdapter mDbHelper;
    private LocateDbAdapter mlcDbHelper;

    private static final String TAG = "ShowTrack";
    private static MapView mMapView;
    private BaiduMap mBaiduMap;
    private boolean bShowTraffic = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_track);
    }

    private void findViews() {
        Log.d(TAG, "find Views");
        // Get the map view from resource file
        mMapView = (MapView) findViewById(R.id.mv);
        mc = mMapView.getController();

        SharedPreferences settings = getSharedPreferences(Setting.SETTING_INFOS, 0);
        String setting_gps = settings.getString(Setting.SETTING_MAP, "10");
        mc.setZoom(Integer.parseInt(setting_gps));

        // ---use the LocationManager class to obtain GPS locations---
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                locationListener);
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

    private void centerOnGPSPosition(View view) {
        Log.d(TAG, "centerOnGPSPosition");
        String provider = "gps";
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location loc = lm.getLastKnownLocation(provider);
        loc = lm.getLastKnownLocation(provider);

        mDefPoint = new GeoPoint((int) (loc.getLatitude() * 1000000),
                (int) (loc.getLongitude() * 1000000));
        mDefCaption = "I'm Here.";
        mc.animateTo(mDefPoint);
        mc.setCenter(mDefPoint);
        mBaiduMap.
        // show Overlay on map.
        MyOverlay mo = new MyOverlay();
        mo.onTap(mDefPoint, mMapView);
        mMapView.getOverlays().add(mo);
    }
}
