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

public class OwnerSingUp extends AppCompatActivity {

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



    }
    public void SuccessRegisterStoreCliked(View v) {
        switch (v.getId()){
            case R.id.SuccessRegisterStore:
                new AlertDialog.Builder(this)
                        .setTitle("등록 완료")
                        .setMessage("\n등록이 완료되었습니다.")
                        .show();//팝업창 생성
                break;

        }
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, mainUI.class);
        //액티비티 시작!
        startActivity(intent);
    }

    }




