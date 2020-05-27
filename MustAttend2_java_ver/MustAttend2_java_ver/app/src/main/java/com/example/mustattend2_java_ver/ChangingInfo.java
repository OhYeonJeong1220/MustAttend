package com.example.mustattend2_java_ver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class ChangingInfo extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changing_info);

        //팝업 버튼
        findViewById(R.id.button1).setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button1:
                new AlertDialog.Builder(this)
                        .setTitle("수정 완료")
                        .setMessage("\n수정이 완료되었습니다.")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();//팝업창 생성
                break;


        }
    }
}
