package com.example.usergym;

public class Notification {
    private String title;
    private String time;
    private int iconResId;

    public Notification(String title, String time, int iconResId) {
        this.title = title;
        this.time = time;
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public int getIconResId() {
        return iconResId;
    }
}