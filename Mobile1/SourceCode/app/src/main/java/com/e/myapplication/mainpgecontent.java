package com.e.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class mainpgecontent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void signIn(View view){
        EditText uname=(EditText) findViewById(R.id.editText);
        EditText pwd=(EditText) findViewById(R.id.editText2);
        String username=uname.getText().toString();
        String password=pwd.getText().toString();

        boolean validationflag=false;
        if(!username.isEmpty() && !password.isEmpty()){
            if(username.equals("saichand") && password.equals("patchala")){
                validationflag=true;
            }
        }
        if(!validationflag){
            Toast toast=Toast.makeText(getApplicationContext(),"Please enter valid credentials..",Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Intent redirect= new Intent(mainpgecontent.this, hmepagecontent.class);
            startActivity(redirect);
        }
    }
}