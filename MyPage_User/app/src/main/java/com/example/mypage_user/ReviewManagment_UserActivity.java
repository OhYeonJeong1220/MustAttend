package com.example.mypage_user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ReviewManagment_UserActivity extends AppCompatActivity {

    private ListView mListView;

    private Button deletebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_managment__user);

        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.listView);

        deletebutton = (Button)findViewById(R.id.deleteButton);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();

    }
    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();


        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sample), "일미 닭갈비" + i, "맛있어요" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }
    public void deleteClicked(View v)
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("리뷰 삭제");
        dlg.setMessage("정말 삭제하시겠습니까?");
        dlg.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ReviewManagment_UserActivity.this,"deleted",Toast.LENGTH_SHORT).show();

            }
        });
        dlg.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ReviewManagment_UserActivity.this,"canceled",Toast.LENGTH_SHORT).show();
            }
        });

        dlg.show();
    }
}
