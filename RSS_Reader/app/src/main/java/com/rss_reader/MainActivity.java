package com.rss_reader;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.rss_reader.data.RSSFeed;
import com.rss_reader.data.RSSItem;
import com.rss_reader.sax.RSSHandler;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public final String RSS_URL = "http://rss.sina.com.cn/news/world/focus15.xml";
    public final String tag = "RSSReader";
    private RSSFeed feed = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RSSFeedTask task = new RSSFeedTask();
        task.execute();
    }

    private RSSFeed getFeed(String urlString)
    {
        try
        {
            URL url = new URL(urlString);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            RSSHandler rssHandler = new RSSHandler();
            xmlreader.setContentHandler(rssHandler);

            InputSource is = new InputSource(url.openStream());
            xmlreader.parse(is);

            return rssHandler.getFeed();
        }
        catch (Exception ee)
        {
            ee.printStackTrace();
            return null;
        }
    }


    private void showListView()
    {
        ListView itemlist = (ListView) findViewById(R.id.itemlist);
        if (feed == null)
        {
            setTitle("访问的RSS无效");
            return;
        }

        SimpleAdapter adapter = new SimpleAdapter(this, feed.getAllItemsForListView(),
                android.R.layout.simple_list_item_2, new String[] { RSSItem.TITLE,RSSItem.PUBDATE },
                new int[] { android.R.id.text1 , android.R.id.text2});
        itemlist.setAdapter(adapter);
        itemlist.setOnItemClickListener(this);
        itemlist.setSelection(0);
    }

    class RSSFeedTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            feed = getFeed(RSS_URL);
            return null;
        }

        protected void onPostExecute(Void result) {
            setTitle(getString(R.string.app_name));
            showListView();
        }

        protected void onPreExecute () {
            setTitle("请稍候");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent itemintent = new Intent(this,ShowDescriptionActivity.class);

        Bundle b = new Bundle();
        b.putString("title", feed.getItem(position).getTitle());
        b.putString("description", feed.getItem(position).getDescription());
        b.putString("link", feed.getItem(position).getLink());
        b.putString("pubdate", feed.getItem(position).getPubDate());

        itemintent.putExtra("android.intent.extra.rssItem", b);
        startActivityForResult(itemintent, 0);
    }
}
