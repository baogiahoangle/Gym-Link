package com.example.usergym;

import java.io.Serializable;

public class Exercise implements Serializable {
    private String name;
    private String duration;
    private int imageResId;

    public Exercise(String name, String duration, int imageResId) {
        this.name = name;
        this.duration = duration;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public int getImageResId() {
        return imageResId;
    }
}
