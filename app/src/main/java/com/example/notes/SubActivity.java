package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.IOException;

public class SubActivity extends AppCompatActivity {
    EditText editText;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        editText = findViewById(R.id.editTextTextMultiLine2);
        Intent intent = getIntent();
        String current = intent.getStringExtra("current");
        index = intent.getIntExtra("index",0);
        editText.setText(current);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (index == MainActivity.notesList.size()){
                    MainActivity.notesList.add(editText.getText().toString());

                }else {
                    MainActivity.notesList.set(index,editText.getText().toString());
                }
                    MainActivity.listView.invalidateViews();
                try {
                    MainActivity.sharedPreferences.edit().putString("notes",ObjectSerializer.serialize(MainActivity.notesList)).apply();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (index == MainActivity.notesList.size()){
            MainActivity.notesList.add(editText.getText().toString());

        }else {
            MainActivity.notesList.set(index,editText.getText().toString());
        }
        MainActivity.listView.invalidateViews();
        try {
            MainActivity.sharedPreferences.edit().putString("notes",ObjectSerializer.serialize(MainActivity.notesList)).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}