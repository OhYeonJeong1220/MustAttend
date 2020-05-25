package com.example.mycollegeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
    public void onLocationButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), TempActivity.class);
        startActivity(intent);
    }


}
