package com.umkc.icp4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent redirect = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getlocmap(View l) {
        redirect = new Intent(MainActivity.this, LocActivity.class);
        startActivity(redirect);
    }

    public void getcamera(View c) {
        redirect = new Intent(MainActivity.this,CameraActivity.class);
        startActivity(redirect);
    }

    public void getVoice(View v) {
        redirect = new Intent(MainActivity.this,RecordActivity.class);
        startActivity(redirect);
    }

    public void saveData(View d) {
        redirect = new Intent(MainActivity.this,StoreActivity.class);
        startActivity(redirect);
    }
}
