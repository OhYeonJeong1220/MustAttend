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


public class SignUp extends AppCompatActivity  {

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


    }

    public void SuccessRegisterCliked(View v){
        switch (v.getId()){
            case R.id.SuccessRegister:
                new AlertDialog.Builder(this)
                        .setTitle("가입 완료")
                        .setMessage("\n가입이 완료되었습니다.")
                        .show();//팝업창 생성
                break;

        }
        Intent intent = new Intent(this, mainUI.class);
        //액티비티 시작!
        startActivity(intent);
    }

}
