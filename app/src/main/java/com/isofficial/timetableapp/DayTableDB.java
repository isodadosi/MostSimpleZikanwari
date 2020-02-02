package com.isofficial.timetableapp;

import io.realm.RealmObject;

public class DayTableDB extends RealmObject {
    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
