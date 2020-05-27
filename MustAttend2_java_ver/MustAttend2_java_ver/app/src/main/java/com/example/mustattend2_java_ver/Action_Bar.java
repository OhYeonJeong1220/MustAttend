package com.example.mustattend2_java_ver;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Action_Bar extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private CheckReservation fragmentReservation = new CheckReservation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        bottomNavigationView = findViewById(R.id.navigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentReservation).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.RecommendItem:{
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout,fragmentReservation)
                                .commitAllowingStateLoss();
                    }
                    default: break;

                }
            }
        });
    }
}

