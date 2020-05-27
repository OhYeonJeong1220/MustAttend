package com.example.mustattend2_java_ver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class StoreListActivity extends Fragment {

    private ListView mListView;
    ViewGroup viewGroup;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull  ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        viewGroup =(ViewGroup)inflater.inflate(R.layout.activity_store_list, container, false);
        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)getActivity().findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
        return viewGroup;
    }

    private void dataSetting(){

        MyAdapter3 mMyAdapter = new MyAdapter3();


        for (int i=0; i<100; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.icon), "가게명_" + i, "추천수_" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }


}
