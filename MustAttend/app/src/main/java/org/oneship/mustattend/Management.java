package org.oneship.mustattend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Management extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
    }
    public void ReviewClicked(View v)
    {
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, ReviewManagment_Owner_Activity.class);
        //액티비티 시작!
        startActivity(intent);
    }
}
