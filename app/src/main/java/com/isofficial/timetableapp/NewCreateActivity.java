package com.isofficial.timetableapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import io.realm.Realm;
import io.realm.RealmResults;

public class NewCreateActivity extends AppCompatActivity {

    CheckBox getu ;
    CheckBox ka ;
    CheckBox sui;
    CheckBox moku;
    CheckBox kin;

    CheckBox time1;
    CheckBox time2;
    CheckBox time3;
    CheckBox time4;
    CheckBox time5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_create);



        getu = findViewById(R.id.getu);
        ka = findViewById(R.id.ka);
        sui = findViewById(R.id.sui);
        moku = findViewById(R.id.moku);
        kin = findViewById(R.id.kin);

        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        time4 = findViewById(R.id.time4);
        time5 = findViewById(R.id.time5);
    }


    public void onCreateButton(View view){
        //                データを削除
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<TimeTableDB> timeResult = realm.where(TimeTableDB.class).findAll();
        final RealmResults<DayTableDB> dayResult = realm.where(DayTableDB.class).findAll();
        realm.beginTransaction();
        timeResult.deleteAllFromRealm();
        dayResult.deleteAllFromRealm();
        realm.commitTransaction();

        onCheck();
        onEmptyContent();

        startActivities(new Intent[]{new Intent(NewCreateActivity.this, MainActivity.class)});
    }

    public void onCheck() {
        Realm realm = Realm.getDefaultInstance();
        if (getu.isChecked()) {
            realm.beginTransaction();
            DayTableDB dayTable = realm.createObject(DayTableDB.class);
            dayTable.setDay("月");
            realm.commitTransaction();
        }
        if (ka.isChecked()) {
            realm.beginTransaction();
            DayTableDB dayTable = realm.createObject(DayTableDB.class);
            dayTable.setDay("火");
            realm.commitTransaction();
        }
        if (sui.isChecked()) {
            realm.beginTransaction();
            DayTableDB dayTable = realm.createObject(DayTableDB.class);
            dayTable.setDay("水");
            realm.commitTransaction();
        }
        if (moku.isChecked()) {
            realm.beginTransaction();
            DayTableDB dayTable = realm.createObject(DayTableDB.class);
            dayTable.setDay("木");
            realm.commitTransaction();
        }
        if (kin.isChecked()) {
            realm.beginTransaction();
            DayTableDB dayTable = realm.createObject(DayTableDB.class);
            dayTable.setDay("金");
            realm.commitTransaction();
        }

        if (time1.isChecked()) {
            realm.beginTransaction();
            TimeTableDB timeTable = realm.createObject(TimeTableDB.class);
            timeTable.setTime("1");
            realm.commitTransaction();
        }
        if (time2.isChecked()) {
            realm.beginTransaction();
            TimeTableDB timeTable = realm.createObject(TimeTableDB.class);
            timeTable.setTime("2");
            realm.commitTransaction();
        }
        if (time3.isChecked()) {
            realm.beginTransaction();
            TimeTableDB timeTable = realm.createObject(TimeTableDB.class);
            timeTable.setTime("3");
            realm.commitTransaction();
        }
        if (time4.isChecked()) {
            realm.beginTransaction();
            TimeTableDB timeTable = realm.createObject(TimeTableDB.class);
            timeTable.setTime("4");
            realm.commitTransaction();
        }
        if (time5.isChecked()) {
            realm.beginTransaction();
            TimeTableDB timeTable = realm.createObject(TimeTableDB.class);
            timeTable.setTime("5");
            realm.commitTransaction();
        }

    }

    public void onEmptyContent(){
        //                データを削除
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<ContentDB> results = realm.where(ContentDB.class).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();

//        データの取得
        RealmResults<DayTableDB> dayTable = realm.where(DayTableDB.class).findAll();
        RealmResults<TimeTableDB> timeTable = realm.where(TimeTableDB.class).findAll();

        for (int number = 0; number < dayTable.size()*timeTable.size(); number++){
            realm.beginTransaction();
            ContentDB content = realm.createObject(ContentDB.class);
            content.setContent(" ");
            content.setId(number);
            realm.commitTransaction();
        }
    }


}
