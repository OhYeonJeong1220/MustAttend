package org.oneship.mustattend;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> dateMonths = new ArrayList<String>();
    ArrayList<String> dateDays = new ArrayList<String>();
    ArrayList<String> timeHours = new ArrayList<String>();
    ArrayList<String> timeMins = new ArrayList<String>();
    ArrayList<String> numberOfPeoples = new ArrayList<String>();

    public void addItem(String item){
        items.add(item);
    }
    public void addMonth(String item){
        dateMonths.add(item);
    }
    public void addDay(String item){
        dateDays.add(item);
    }
    public void addHour(String item){
        timeHours.add(item);
    }
    public void addMin(String item){
        timeMins.add(item);
    }
    public void addNum(String item){
        numberOfPeoples.add(item);
    }



    public void setItems(ArrayList<String> items) {
        this.items = items;
    }
    public void setMonths(ArrayList<String> items) {
        this.dateMonths = items;
    }
    public void setDays(ArrayList<String> items) {
        this.dateDays = items;
    }
    public void setHours(ArrayList<String> items) {
        this.timeHours = items;
    }
    public void setMins(ArrayList<String> items) {
        this.timeMins = items;
    }
    public void setNums(ArrayList<String> items) {
        this.numberOfPeoples = items;
    }




    public String getItem(int position) {
        return items.get(position);
    }
    public String getMonth(int position) {
        return dateMonths.get(position);
    }
    public String getDay(int position) {
        return dateDays.get(position);
    }
    public String getHour(int position) {
        return timeHours.get(position);
    }
    public String getMin(int position) {
        return timeMins.get(position);
    }
    public String getNum(int position) {
        return numberOfPeoples.get(position);
    }



    public void setItem(int position, String item) {
        items.set(position, item);
    }
    public void setMonth(int position, String item) {
        dateMonths.set(position, item);
    }
    public void setDay(int position, String item) {
        dateDays.set(position, item);
    }
    public void setHour(int position, String item) {
        timeHours.set(position, item);
    }
    public void setMin(int position, String item) {
        timeMins.set(position, item);
    }
    public void setNum(int position, String item) {
        numberOfPeoples.set(position, item);
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
        String item = items.get(position);
        holder.setItem(item);

        String item2 = dateMonths.get(position);
        holder.setMonth(item2);

        String item3 = dateDays.get(position);
        holder.setDay(item3);

        String item4 = timeHours.get(position);
        holder.setHour(item4);

        String item5 = timeMins.get(position);
        holder.setMin(item5);

        String item6 = numberOfPeoples.get(position);
        holder.setNum(item6);
    }

    @Override
    public int getItemCount() {
        // 객체의 수
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.purpose);
            textView2 = itemView.findViewById(R.id.month);
            textView3 = itemView.findViewById(R.id.day);
            textView4 = itemView.findViewById(R.id.hour);
            textView5 = itemView.findViewById(R.id.min);
            textView6 = itemView.findViewById(R.id.num);

        }

        public void setItem(String item) {
            textView.setText(item);
        }
        public void setMonth(String item) {
            textView2.setText(item);
        }
        public void setDay(String item) {
            textView3.setText(item);
        }
        public void setHour(String item) {
            textView4.setText(item);
        }
        public void setMin(String item) {
            textView5.setText(item);
        }
        public void setNum(String item) {
            textView6.setText(item);
        }
    }
}