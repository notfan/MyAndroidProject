package com.example.xiwen.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.xiwen.myapplication.MESSAGE";
    static final int PICK_CONTACT_REQUEST = 1;  // The request code
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (sharedText != null) {
                    EditText editText = (EditText) findViewById(R.id.edit_message);
                    editText.setText(sharedText);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem =  menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        return true;
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.menu_item_listview:
                testListView();
                break;
            case R.id.menu_item_dialog:
                testDialog();
                break;
            case R.id.menu_item_notification_toast:
                testNotificationAndToast();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void selectContact(View view) {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    public void shareContact(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        TextView textView = (TextView) findViewById(R.id.phone_number);
        sendIntent.putExtra(Intent.EXTRA_TEXT,textView.getText());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }

    public void testCheckBox(View view) {
        Intent intent = new Intent(this,CheckBoxActivity.class);
        startActivity(intent);
    }

    public void testRadioBox(View view) {
        Intent intent = new Intent(this,RadioBoxActivity.class);
        startActivity(intent);
    }

    public void testSpinner(View view) {
        Intent intent = new Intent(this,SpinnerActivity.class);
        startActivity(intent);
    }

    public void testAutoComplete(View view) {
        Intent intent = new Intent(this,AutoCompleteTextViewActivity.class);
        startActivity(intent);
    }

    public void testDatePicker(View view) {
        Intent intent = new Intent(this,DatePickerActivity.class);
        startActivity(intent);
    }

    public void testTimePicker(View view) {
        Intent intent = new Intent(this,TimePickerActivity.class);
        startActivity(intent);
    }

    public void testProgressBar(View view) {
        Intent intent = new Intent(this,ProgressBarActivity.class);
        startActivity(intent);
    }

    public void testSeekBar(View view) {
        Intent intent = new Intent(this,SeekBarActivity.class);
        startActivity(intent);
    }

    public void testRatingBar(View view) {
        Intent intent = new Intent(this,RatingBarActivity.class);
        startActivity(intent);
    }

    public void testImageView(View view) {
        Intent intent = new Intent(this,ImageViewActivity.class);
        startActivity(intent);
    }

    public void testImageShow(View view) {
        Intent intent = new Intent(this,ImageShowActivity.class);
        startActivity(intent);
    }

    public void testGridView(View view) {
        Intent intent = new Intent(this,GridViewActivity.class);
        startActivity(intent);
    }

    public void testTabDemo(View view) {
        Intent intent = new Intent(this,TabDemoActivity.class);
        startActivity(intent);
    }

    public void testListView() {
        Intent intent = new Intent(this,ListViewActivity.class);
        startActivity(intent);
    }

    public void testDialog() {
        Intent intent = new Intent(this,DialogDemoActivity.class);
        startActivity(intent);
    }

    public void testNotificationAndToast() {
        Intent intent = new Intent(this,ToastAndNotificationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_CONTACT_REQUEST) {
            if(resultCode == RESULT_OK) {
                Uri contactUri = data.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                Cursor cursor = getContentResolver().query(contactUri,projection,null,null,null);
                cursor.moveToFirst();

                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);

                TextView textView = (TextView) findViewById(R.id.phone_number);
                textView.setText(number);
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, number);
                setShareIntent(shareIntent);
            }
        }
    }


}
