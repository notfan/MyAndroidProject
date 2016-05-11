package com.example.xiwen.myapplication;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class ContentProviderActivity extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] projection = {ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String[] projection_query  = {ContactsContract.Data._ID, projection[0], projection[1]};
        Cursor c = getContentResolver().query(ContactsContract.Data.CONTENT_URI, projection_query, null, null, null);
        ListAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, c,
                projection,
                new int[] { android.R.id.text1, android.R.id.text2 },0);
        setListAdapter(adapter);
    }

}