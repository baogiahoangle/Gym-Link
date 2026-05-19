package com.example.usergym;

public class Equipment {
    private String name;
    private int imageResId;

    public Equipment(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
