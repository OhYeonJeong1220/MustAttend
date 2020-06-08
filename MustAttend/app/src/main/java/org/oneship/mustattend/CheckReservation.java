package org.oneship.mustattend;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class CheckReservation extends Fragment {
    ViewGroup viewGroup;
    String user_email;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull  ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        viewGroup =(ViewGroup)inflater.inflate(R.layout.activity_check_reservation, container, false);
        user_email = this.getArguments().getString("user_email");
        System.out.println("**********예약 조희 : "+user_email+"***********");

        Intent intent = new Intent(getActivity().getApplicationContext(), RealReservation.class);
        intent.putExtra("user_email",user_email); //intent로 mainUI activity에 전달할 이메일
        startActivity(intent);

        return viewGroup;
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_check_reservation);
//
//    }
}
