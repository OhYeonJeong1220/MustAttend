package org.oneship.mustattend;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivitiy extends AppCompatActivity {

    TestFragment testFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activitiy);

        testFragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.TestFragment);
        menuFragment = new MenuFragment(); // 객체 생성, 아직 안 올라감
    }

    public void onFragmentChanged(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, testFragment).commit();
        } else if (index == 1 ){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
        }
    }
}
