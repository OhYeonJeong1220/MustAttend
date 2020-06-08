package org.oneship.mustattend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;



public class TestFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 올라가는 시점이 달라서 false
        ViewGroup rootView =  (ViewGroup) inflater.inflate(R.layout.fragment_test, container, false);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestActivitiy activitiy = (TestActivitiy) getActivity(); // 내가 올라간 액티비티 참조a
                activitiy.onFragmentChanged(1);
            }
        });

        return rootView;
    }
}
