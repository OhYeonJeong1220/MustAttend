package org.oneship.mustattend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 올라가는 시점이 달라서 false
        return inflater.inflate(R.layout.fragment_menu, container, false);

    }

}
