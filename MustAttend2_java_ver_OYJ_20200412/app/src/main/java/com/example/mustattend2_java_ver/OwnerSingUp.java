package com.example.mustattend2_java_ver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OwnerSingUp extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_sing_up);

        EditText brand_phone = (EditText) findViewById(R.id.ShopPhone);
        brand_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        final NumberPicker numberPickerCapacity = (NumberPicker) findViewById(R.id.Capacity);

        numberPickerCapacity.setMaxValue(10000);
        numberPickerCapacity.setMinValue(0);
        numberPickerCapacity.setWrapSelectorWheel(false);
        numberPickerCapacity.setValue(0);

        //라디오 버튼 설정
        final RadioButton r_btn1 = (RadioButton) findViewById(R.id.r_btn1);
        final RadioButton r_btn2 = (RadioButton) findViewById(R.id.r_btn2);



        //라디오 버튼 클릭 리스너
        RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OwnerSingUp.this, "r_btn1 : " + r_btn1.isChecked() + "r_btn2 : " + r_btn2.isChecked(), Toast.LENGTH_SHORT).show();
            }
        };
        //라디오 그룹 클릭 리스너
        RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener()
        { @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i)
        {
            if(i == R.id.r_btn1)
            { Toast.makeText(OwnerSingUp.this, "가능", Toast.LENGTH_SHORT).show();
            }
            else if(i == R.id.r_btn2)
            { Toast.makeText(OwnerSingUp.this, "불가능", Toast.LENGTH_SHORT).show();
            }
        }
        };
        //라디오 그룹 설정
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        //팝업 버튼
        findViewById(R.id.button1).setOnClickListener(this);

    }

    //팝업창 생성
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button1:
                new AlertDialog.Builder(this)
                        .setTitle("등록 완료")
                        .setMessage("\n등록이 완료되었습니다.")
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

