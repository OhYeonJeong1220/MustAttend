package org.oneship.mustattend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onKoreanButtonClicked(View v)
    {
        Toast.makeText(getApplicationContext(),"한식", Toast.LENGTH_SHORT).show();
    }
    public void onUSButtonClicked(View v)
    {
        Toast.makeText(getApplicationContext(),"양식", Toast.LENGTH_SHORT).show();
    }
    public void onJapaneseButtonClicked(View v)
    {
        Toast.makeText(getApplicationContext(),"일식", Toast.LENGTH_SHORT).show();
    }
    public void positionButtonClicked(View v){
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, PositioningActivity.class);
        //액티비티 시작!
        startActivity(intent);
    }
    public void MyButtonClicked(View v){
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, Management_user.class);
        //액티비티 시작!
        startActivity(intent);
    }



}

