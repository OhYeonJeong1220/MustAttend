package org.oneship.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        final NumberPicker monthPicker = findViewById(R.id.picker_month);
        final NumberPicker dayPicker = findViewById(R.id.picker_day);

        final NumberPicker hourPicker = findViewById(R.id.picker_hour);
        final NumberPicker minutePicker = findViewById(R.id.picker_minute);

        final NumberPicker numPicker = findViewById(R.id.picker_num);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);

        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);

        numPicker.setMinValue(0);
        numPicker.setMaxValue(30);

        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
    }

    //private DatePickerDialog.OnDateSetListener listener;
    //private TimePickerDialog.OnTimeSetListener listener2;

    //public void setListener(DatePickerDialog.OnDateSetListener listener) {
    //    this.listener = listener;
    //}

    //public void setListener2(TimePickerDialog.OnTimeSetListener listener2) {
    //    this.listener2 = listener2;
    //}
}
