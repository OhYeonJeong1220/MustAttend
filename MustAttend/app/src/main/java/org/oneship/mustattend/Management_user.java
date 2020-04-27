package org.oneship.mustattend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Management_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_user);
    }
    public void ReviewClicked(View v)
    {
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, ReviewManagment_UserActivity.class);
        //액티비티 시작!
        startActivity(intent);
    }
    public void LogoutClicked(View v)
    {
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, LogoutActivity.class);
        //액티비티 시작!
        startActivity(intent);
    }
}