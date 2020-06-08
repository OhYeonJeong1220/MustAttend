package org.oneship.mustattend;

import android.graphics.drawable.Drawable;

public class Reservation {
    // 한 예약 내역 item
    String name;
    Drawable icon;

    public Reservation(String name) {
        this.name = name;
    }

    public Reservation(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
