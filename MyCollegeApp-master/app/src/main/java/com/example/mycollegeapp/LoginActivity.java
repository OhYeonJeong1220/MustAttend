package com.example.mycollegeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void loginButtonClicked(View v){
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, MainActivity.class);
        //액티비티 시작!
        startActivity(intent);
    }
    public void SignUpClicked(View v)
    {
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, SignUpActivity.class);
        //액티비티 시작!
        startActivity(intent);
    }
}
