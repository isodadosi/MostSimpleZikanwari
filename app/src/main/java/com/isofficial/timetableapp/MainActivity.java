package com.isofficial.timetableapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    TableLayout table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Realm.init(this);

        table = findViewById(R.id.table);

    onCreateTable();
    //onCreateEmptyContent();

    }

    public void onCreateTable(){
        table.removeAllViews();
        int number = 0;
        Realm realm = Realm.getDefaultInstance();
        RealmResults<DayTableDB> dayTable = realm.where(DayTableDB.class).findAll();
        RealmResults<TimeTableDB> timeTable = realm.where(TimeTableDB.class).findAll();
        RealmResults<ContentDB> content = realm.where(ContentDB.class).findAll();

        realm.beginTransaction();
        for (int y = -1 ; y < timeTable.size(); y++) {
            TableRow tableRow = new TableRow(this);
//        曜日の部分
            for (int x = 0; x < dayTable.size(); x++) {
                if (y == -1) {
                    if (x == 0) {
                        TextView textNone = new TextView(this);
                        textNone.setText(" ");
                        tableRow.addView(textNone);
                        //table.setColumnStretchable(0, true);
                    }
                    TextView textDay = new TextView(this);
                    textDay.setText(dayTable.get(x).getDay());
                    textDay.setGravity(1);
                    tableRow.addView(textDay);
                    //table.setColumnStretchable(x + 1, true);
                }
//                時間と内容
                else {
//                    時間
                    if (x == 0) {
                        TextView textTime = new TextView(this);
                        textTime.setText(timeTable.get(y).getTime());
                        textTime.setGravity(1);
                        tableRow.addView(textTime);
                    }
//                    内容
                    EditText editText = new EditText(this);
                    editText.setText(content.get(number).getContent());
                    editText.setId(number);
                    editText.setBackgroundColor(Color.WHITE);
                    number++;
                    tableRow.addView(editText);
                }
            }
            table.addView(tableRow);
        }
        realm.commitTransaction();

        for (int i = 0; i < dayTable.size() + 1; i++){
            table.setColumnStretchable(i,true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.create_button) {
            startActivities(new Intent[]{new Intent(MainActivity.this, NewCreateActivity.class)});
        }

        if (id == R.id.save_button) {
            Toast.makeText(this,"保存しました",Toast.LENGTH_SHORT).show();
            onSaveButton();
            onCreateTable();
        }

        return super.onOptionsItemSelected(item);
    }


    public void onSaveButton(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ContentDB> contentCount = realm.where(ContentDB.class).findAll();
        realm.beginTransaction();
        contentCount.deleteAllFromRealm();
        realm.commitTransaction();

        //        データの取得
        RealmResults<DayTableDB> dayTable = realm.where(DayTableDB.class).findAll();
        RealmResults<TimeTableDB> timeTable = realm.where(TimeTableDB.class).findAll();


        for (int count = 0; count < dayTable.size()*timeTable.size(); count++){
            realm.beginTransaction();
            ContentDB content = realm.createObject(ContentDB.class);
            EditText text = findViewById(count);
            content.setId(count);
            content.setContent(text.getText().toString());
            realm.commitTransaction();
        }
    }
}
