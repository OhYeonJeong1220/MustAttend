package org.oneship.mustattend;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RealReservation extends AppCompatActivity {
    String user_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_reservation);
        user_email = getIntent().getStringExtra("user_email");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ReservationAdapter adapter = new ReservationAdapter();

        adapter.addItem(new Reservation("일미닭갈비"));
        adapter.addItem(new Reservation("폼프리츠"));
        adapter.addItem(new Reservation("삼미닭갈비"));

        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, mainUI.class);
        intent.putExtra("user_email",user_email); //intent로 mainUI activity에 전달할 이메일
        startActivity(intent);
    }

}
