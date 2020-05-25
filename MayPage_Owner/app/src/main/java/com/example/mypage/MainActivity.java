package com.example.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ReviewClicked(View v)
    {
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, ReviewManagment_Owner_Activity.class);
        //액티비티 시작!
        startActivity(intent);
    }
    public void onLeaveButtonClicked(View v){
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, ByeActivity.class);
        //액티비티 시작!
        startActivity(intent);
    }

}
