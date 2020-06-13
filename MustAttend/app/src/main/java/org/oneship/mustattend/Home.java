package org.oneship.mustattend;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Home extends Fragment {
    String user_email; //이메일 세션
    Location location;

    ViewGroup viewGroup;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull  ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        viewGroup =(ViewGroup)inflater.inflate(R.layout.activity_home, container, false);

        user_email = this.getArguments().getString("user_email");
        System.out.println("**********Home : "+user_email+"***********");
        // 지도 보여주기
        ImageButton imgbutton = viewGroup.findViewById(R.id.imageButton);
        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainUI activitiy = (mainUI) getActivity(); // 내가 올라간 액티비티 참조
                activitiy.onMyMap();
            }
        });

        // 내 주소 텍스트로
        ImageButton markerbutton = viewGroup.findViewById(R.id.positionButton);
        markerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainUI activity = (mainUI) getActivity();
                activity.onMyPosition();
            }
        });

        Button store = viewGroup.findViewById(R.id.storelist);
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainUI activity = (mainUI) getActivity();
                activity.onStoreList();
            }
        });

        return viewGroup;
    }



}
