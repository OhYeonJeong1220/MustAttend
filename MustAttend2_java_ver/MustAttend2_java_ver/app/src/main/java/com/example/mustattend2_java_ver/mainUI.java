package com.example.mustattend2_java_ver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class mainUI extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private Home fragmentHome = new Home();
    private MyPage fragmentMypage = new MyPage();
    private CheckReservation fragmentReservation = new CheckReservation();
    private StoreListActivity fragmentRecommend = new StoreListActivity();
    private FavoriteShop fragmentFavoriteShop = new FavoriteShop();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        bottomNavigationView = findViewById(R.id.navigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentHome).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.HomeItem:{//홈
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout,fragmentHome)
                                .commitAllowingStateLoss();
                    }
                    case R.id.FavoriteItem:{//찜
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout,fragmentFavoriteShop)
                                .commitAllowingStateLoss();
                    }

                    case R.id.RecommendItem:{//추천
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout,fragmentFavoriteShop)
                                .commitAllowingStateLoss();
                    }
                    case R.id.ListItem:{//예약내역
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout,fragmentReservation)
                                .commitAllowingStateLoss();
                    }
                    case R.id.MyPageItem:{//My
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout,fragmentMypage)
                                .commitAllowingStateLoss();
                    }
                    default: break;

                }
            }
        });
    }


}
