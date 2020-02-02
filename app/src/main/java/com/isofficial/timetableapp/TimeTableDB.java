package com.isofficial.timetableapp;

import io.realm.RealmObject;

public class TimeTableDB extends RealmObject {
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

