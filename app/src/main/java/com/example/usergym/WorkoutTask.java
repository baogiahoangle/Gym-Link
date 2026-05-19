package com.example.usergym;

import java.io.Serializable;

public class WorkoutTask implements Serializable {
    private String time;
    private String name;
    private String duration;

    public WorkoutTask(String time, String name, String duration) {
        this.time = time;
        this.name = name;
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }
}