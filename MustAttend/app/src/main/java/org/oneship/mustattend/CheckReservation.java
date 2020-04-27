package org.oneship.mustattend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class CheckReservation extends Fragment {
    ViewGroup viewGroup;
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull  ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        viewGroup =(ViewGroup)inflater.inflate(R.layout.activity_check_reservation, container, false);
        return viewGroup;
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_check_reservation);
//
//    }
}
