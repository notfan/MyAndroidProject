package com.example.xiwen.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
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
    private View hsv;
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
        hsv = findViewById(R.id.hsv);
        mSwitcher.setOnLongClickListener(new ImageSwitcher.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (bShowGallery == 1) {
                    hsv.animate().alpha(0f).setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    hsv.setVisibility(View.INVISIBLE);
                                }
                            });
                    bShowGallery = 0;
                } else {
                    hsv.setAlpha(0f);
                    hsv.setVisibility(View.VISIBLE);
                    hsv.animate().alpha(1f).setDuration(500).setListener(null);
                    bShowGallery = 1;
                }
                return true;
            }
        });

        hsvGallery = (LinearLayout) findViewById(R.id.hsvGallery);
        for (int i = 0;i < images_thumb.length; i++) {
            hsvGallery.addView(insertImage(i));
        }
    }

    private View insertImage(int nIndex) {
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        imageView.setLayoutParams(new ViewGroup.LayoutParams((int) (60 * scale + 0.5f), (int) (60 * scale + 0.5f)));
        imageView.setImageResource(images_thumb[nIndex]);
        imageView.setTag(nIndex);
        imageView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwitcher.setImageResource(images[(int) (v.getTag())]);
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
