package com.example.mustattend2_java_ver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
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
        Intent intent = new Intent(this, SignUp.class);
        //액티비티 시작!
        startActivity(intent);
    }
    @Override
    public void onBackPressed(){
        Toast.makeText(this, "앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        finishAffinity();
        System.runFinalization();
        System.exit(0);
    }


}
