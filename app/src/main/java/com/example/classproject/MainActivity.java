package com.example.classproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            TextView etId = findViewById(R.id.etId);
            TextView etName = findViewById(R.id.etName);

            UniversityDB universityDBHelper = new UniversityDB(this);
            SQLiteDatabase universityDB = universityDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", etId.getText().toString());
            values.put("id", etId.getText().toString());
            universityDB.insert("students", null, values);
            universityDB.close();
        });


    }
}