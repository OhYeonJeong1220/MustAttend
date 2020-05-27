package com.example.mustattend2_java_ver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

public class ReviewManagment_Owner_Activity extends AppCompatActivity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_managment__owner_);

        listview = (ListView)findViewById(R.id.ReviewListView);

        dataSetting();
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();


        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sample), "name_" + i, "contents_" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        listview.setAdapter(mMyAdapter);
    }

}
