package com.example.xiwen.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WebServiceActivity extends AppCompatActivity {
    private static final String SERVICE_URL = "http://www.webxml.com.cn/webservices/weatherwebservice.asmx/getWeatherbyCityName";
    private TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void getWeather(View view) {
        String cityName = ((TextView)findViewById(R.id.city_name)).getText().toString();
        if(cityName == null){
            return;
        }
        mWeatherTextView = (TextView)findViewById(R.id.weather);
        WebServiceTask task = new WebServiceTask();
        task.execute(cityName);
    }
    class WebServiceTask extends AsyncTask<String,Void,String> {//继承AsyncTask

        @Override
        protected String doInBackground(String... strings) {//处理后台执行的任务，在后台线程执行
            String result = "";
            String readLine = null;
            String cityName = strings[0];
            try {
                URL url = new URL(SERVICE_URL);
                HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false);
                urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConn.setRequestProperty("Charset","utf-8");
                urlConn.connect();
                DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
                dop.writeBytes("theCityName=" + URLEncoder.encode(cityName, "utf-8"));
                dop.flush();
                dop.close();

                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                while((readLine=bufferReader.readLine())!=null){
                    result += readLine;
                }
                bufferReader.close();
                urlConn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        protected void onPostExecute(String result) {//后台任务执行完之后被调用，在ui线程执行
            mWeatherTextView.setText(result);
        }

        protected void onPreExecute () {//在 doInBackground(Params...)之前被调用，在ui线程执行
            mWeatherTextView.setText("请稍候...");
        }
    }
}
