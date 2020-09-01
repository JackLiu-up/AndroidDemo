package com.free.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Image extends AppCompatActivity {

    private ImageView mIv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mIv3 = findViewById(R.id.im3);
        Glide.with(this).load("https://ss0.bdstatic.com/5av1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png").into(mIv3);
    }
}