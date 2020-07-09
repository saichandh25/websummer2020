package com.e.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class hmepagecontent extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
    }
    public void logout(View view){
        Intent redirect= new Intent(hmepagecontent.this, mainpgecontent.class);
        startActivity(redirect);
    }
}