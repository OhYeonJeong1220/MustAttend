package org.oneship.mustattend;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    ArrayList<Reservation> items = new ArrayList<Reservation>();

    public void addItem(Reservation item){
        items.add(item);
    }

    public void setItems(ArrayList<Reservation> items) {
        this.items = items;
    }

    public Reservation getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Reservation item) {
        items.set(position, item);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewHolder 생성할 시점에 자동 호출
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.reservation_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 지나간 거 재사용
        Reservation item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        // 객체의 수
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.reservationtext);

        }

        public void setItem(Reservation item) {
            textView.setText(item.getName());

        }
    }
}
