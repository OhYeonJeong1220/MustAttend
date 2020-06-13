package org.oneship.mustattend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class StoreActivity extends AppCompatActivity {
    LinearLayout container;
    private ListView mListView;

    String user_email; //이메일 세션

    // 받은 가게 정보
    String email;
    String store_name; // 가게이름
    String store_phonenum; // 전화번호
    String store_parking; // 주차여부
    String store_maxclientnum; // 수용인원

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        //이메일 세션
        Intent intent = getIntent();
        user_email = intent.getExtras().getString("user_email");

        System.out.println("**********StoreActivity : "+user_email+"***********");

        store_name = getIntent().getStringExtra("store_name"); //activity->activity로 값 전달 받을 때
        TextView name = (TextView) findViewById(R.id.txtname);
        name.setText(store_name.replace("\"",""));

        container = findViewById(R.id.container);

        Button button1 = findViewById(R.id.register);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayout1();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayout2();

            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayout3();

            }
        });

    }

    public void addLayout1() {
        container.removeAllViews();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sub_menu, container, true);
    }

    public void addLayout2() {
        container.removeAllViews();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sub_resv, container, true);

        store_phonenum = getIntent().getStringExtra("store_phonenum"); //activity->activity로 값 전달 받을 때
        store_parking = getIntent().getStringExtra("store_parking"); //activity->activity로 값 전달 받을 때
        store_maxclientnum = getIntent().getStringExtra("store_maxclientnum"); //activity->activity로 값 전달 받을 때

        TextView textview_phone = findViewById(R.id.txtphone);
        TextView textView_parking = findViewById(R.id.txtparking);
        TextView textView_num = findViewById(R.id.txtnum);

        textview_phone.append(store_phonenum.replace("\"","")); // 전화번호 뿌리기
        textView_parking.append(store_parking.replace("\"","")); // 주차여부 뿌리기
        textView_num.append(store_maxclientnum.replace("\"","")); // 수용인원 뿌리기

        Button button = findViewById(R.id.btn_reserve);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreActivity.this, BookingActivity.class);
                intent.putExtra("user_email",user_email); //intent로 다음 activity에 전달할 이메일
                intent.putExtra("store_name",store_name); //intent로 다음 activity에 전달할 이메일
                StoreActivity.this.startActivity(intent);
            }
        });
    }

    public void addLayout3() {
        container.removeAllViews();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sub_revi, container, true);
        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();


        for (int i=1; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "리뷰_" + i, "별점_" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }

    public void onReservButtonClicked (View v){
        Toast.makeText(this,"예약 신청이 완료되었습니다.", Toast.LENGTH_LONG).show();
    }
}