package com.example.classproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etId;
    EditText etName;
    TextView tvId;
    TextView tvName;
    UniversityDB universityDBHelper;
    Cursor data;
    SQLiteDatabase universityDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnNext = findViewById(R.id.btnNext);
        Button btnPreview = findViewById(R.id.btnPreview);
        Button btnRead = findViewById(R.id.btnRead);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        tvId = findViewById(R.id.tvId);
        tvName = findViewById(R.id.tvName);
        universityDBHelper = new UniversityDB(this);

        btnRead.setOnClickListener(view -> {
            universityDB = universityDBHelper.getReadableDatabase();
             data = universityDB.rawQuery("SELECT * FROM students", null);
        });

        btnAdd.setOnClickListener(view -> {
            universityDB = universityDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", etId.getText().toString());
            values.put("name", etId.getText().toString());
            universityDB.insert("students", null, values);
            universityDB.close();
        });

        btnNext.setOnClickListener(view -> {
            if (data.moveToNext())
            {
                tvId.setText(data.getString(0));
                tvName.setText(data.getString(1));
            }
            else
            {
                if (data.moveToFirst())
                {
                    tvId.setText(data.getString(0));
                    tvName.setText(data.getString(1));
                }
            }
        });
        btnPreview.setOnClickListener(view -> {
            if (data.moveToPrevious())
            {
                tvId.setText(data.getString(0));
                tvName.setText(data.getString(1));
            }
            else
            {
                if (data.moveToLast())
                {
                    tvId.setText(data.getString(0));
                    tvName.setText(data.getString(1));
                }
            }
        });
        btnDelete.setOnClickListener(view -> {
            universityDB.delete("students", "id = ?", new String[]{tvId.getText().toString()});
        });
        btnUpdate.setOnClickListener(view -> {
            ContentValues values = new ContentValues();
            values.put("name", etId.getText().toString());
            universityDB.update("students", values, "id = ?", new String[]{tvId.getText().toString()});
        });


    }
}