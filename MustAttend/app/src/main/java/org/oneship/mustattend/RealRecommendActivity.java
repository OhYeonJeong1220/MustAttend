package org.oneship.mustattend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class RealRecommendActivity extends AppCompatActivity {
    String user_email; //로그인 세션
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_recommend);
        user_email = getIntent().getStringExtra("user_email"); //이메일 세션 전달 받기
        System.out.println("************진짜 추천 화면"+user_email+"****************");

        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
    }

    private void dataSetting(){

        MyAdapter3 mMyAdapter = new MyAdapter3();


        for (int i=0; i<50; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "가게명_" + i, "거리_" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, mainUI.class);
        intent.putExtra("user_email",user_email); //intent로 mainUI activity에 전달할 이메일
        startActivity(intent);
    }
}