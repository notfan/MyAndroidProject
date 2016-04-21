package com.example.xiwen.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.Gravity;
import android.widget.ViewSwitcher;

public class ImageShowActivity extends AppCompatActivity implements
        ViewSwitcher.ViewFactory {
    private LinearLayout hsvGallery;
    private ImageSwitcher mSwitcher;
    private int bShowGallery = 1;
    private Integer[] images_thumb = {
            R.drawable.sample_thumb_0,
            R.drawable.sample_thumb_1,
            R.drawable.sample_thumb_2,
            R.drawable.sample_thumb_3,
            R.drawable.sample_thumb_4,
            R.drawable.sample_thumb_5,
            R.drawable.sample_thumb_6,
            R.drawable.sample_thumb_7,
    };

    private Integer[] images = {
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
        mSwitcher.setFactory(this);
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));
        mSwitcher.setImageResource(images[0]);
        mSwitcher.setOnClickListener(new ImageSwitcher.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bShowGallery == 1){
                    findViewById(R.id.hsv);
                    bShowGallery = 0;
                }
                else{
                    bShowGallery = 1;
                }
            }
        });

        hsvGallery = (LinearLayout) findViewById(R.id.hsvGallery);
        for (int i = 0;i < images_thumb.length; i++) {
            hsvGallery.addView(insertImage(i));
        }
    }

    private View insertImage(int nIndex) {
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(240, 240));
        imageView.setImageResource(images_thumb[nIndex]);
        imageView.setTag(nIndex);
        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwitcher.setImageResource(images[(int)(v.getTag())]);
            }
        });
        return imageView;
    }
    public View makeView() {
        ImageView i = new ImageView(this);
        i.setBackgroundColor(0xFF000000);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.MATCH_PARENT,
                ImageSwitcher.LayoutParams.MATCH_PARENT));
        return i;
    }
}
