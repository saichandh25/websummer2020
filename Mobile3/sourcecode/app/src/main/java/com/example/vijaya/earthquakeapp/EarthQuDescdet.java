package com.example.vijaya.earthquakeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class EarthQuDescdet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabtn = (FloatingActionButton) findViewById(R.id.fab);
        fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "actions to be happen", Snackbar.LENGTH_LONG)
                        .setAction("string", null).show();

            }
        });
        ImageView imgvw = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        String image_path= intent.getStringExtra("imagePath");
        Uri diskuri = Uri.parse(image_path);
        imgvw.setImageURI(diskuri);
    }

}