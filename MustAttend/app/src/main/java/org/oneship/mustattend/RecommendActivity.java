package org.oneship.mustattend;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class RecommendActivity extends Fragment {
    String user_email; //이메일 세션
    ViewGroup viewGroup;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull  ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        viewGroup =(ViewGroup)inflater.inflate(R.layout.activity_recommend, container, false);
        user_email = this.getArguments().getString("user_email"); //이메일 세션 받기
        System.out.println("**********추천 화면 : "+user_email+"***********");
        Intent intent = new Intent(getActivity().getApplicationContext(), RealRecommendActivity.class);
        intent.putExtra("user_email",user_email); //intent로 mainUI activity에 전달할 이메일
        startActivity(intent);

        return viewGroup;
    }



}
