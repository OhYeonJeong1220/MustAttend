package com.example.mustattend2_java_ver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyPage extends Fragment {

    ViewGroup viewGroup;
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull  ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        viewGroup =(ViewGroup)inflater.inflate(R.layout.activity_my_page, container, false);
        Button button1 = (Button)viewGroup.findViewById(R.id.ReviewButton);
        Button button2 = (Button)viewGroup.findViewById(R.id.ChangeInfoButton);
        Button button3 = (Button)viewGroup.findViewById(R.id.StoreButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SubActivity로 가는 인텐트를 생성
                Intent intent = new Intent(getActivity(), ReviewManagment_UserActivity.class);
                //액티비티 시작!
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SubActivity로 가는 인텐트를 생성
                Intent intent = new Intent(getActivity(), ChangingInfo.class);
                //액티비티 시작!
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SubActivity로 가는 인텐트를 생성
                Intent intent = new Intent(getActivity(), Owner_myPage.class);
                //액티비티 시작!
                startActivity(intent);
            }
        });
        return viewGroup;

    }

}
