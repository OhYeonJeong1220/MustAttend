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

import java.security.acl.Owner;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // 연락처 입력시 하이픈(-) 자동 입력.
        EditText brand_phone = (EditText) findViewById(R.id.phone);
        brand_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        //생년월일 numberpicker 설정
        final NumberPicker numberPickerYear = (NumberPicker) findViewById(R.id.year);
        final NumberPicker numberPickerMonth = (NumberPicker) findViewById(R.id.month);
        final NumberPicker numberPickerDay = (NumberPicker) findViewById(R.id.day);

        numberPickerYear.setMaxValue(3000);
        numberPickerYear.setMinValue(1900);
        numberPickerYear.setWrapSelectorWheel(false);
        numberPickerYear.setValue(2000);

        numberPickerMonth.setMaxValue(12);
        numberPickerMonth.setMinValue(1);
        numberPickerMonth.setWrapSelectorWheel(true);
        numberPickerMonth.setValue(1);

        numberPickerDay.setMaxValue(31);
        numberPickerDay.setMinValue(1);
        numberPickerDay.setWrapSelectorWheel(true);
        numberPickerDay.setValue(1);

        findViewById(R.id.button1).setOnClickListener(this);

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckReservation.class);
                startActivity(intent);
            }
        });

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button1:
                new AlertDialog.Builder(this)
                        .setTitle("가입 완료")
                        .setMessage("\n가입이 완료되었습니다.")
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
