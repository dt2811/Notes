package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashSet;

public class note extends AppCompatActivity {
int pos=0;
 EditText t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Intent i = getIntent();
        final String notes=i.getStringExtra("note");
        pos = i.getIntExtra("pos", -1);
        if (MainActivity.notes.size() == 0) {
            MainActivity.notes.add(" ");

        } else {
           t = findViewById(R.id.textView);
           t.setText(notes);
            CheckBox s=findViewById(R.id.checkBox);
           //Button s = findViewById(R.id.button);
            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                         if(!t.getText().toString().equals(notes)){

                         MainActivity.adapter.remove(notes);
                    MainActivity.notes.add(pos, t.getText().toString());
                    MainActivity.adapter.notifyDataSetChanged();
                             SharedPreferences s=getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                             HashSet<String> h=new HashSet<>(MainActivity.notes);
                             s.edit().putStringSet("notes",h).apply();

                }
                else{
                    MainActivity.adapter.notifyDataSetChanged();
                }
                    Toast.makeText(note.this, "SAVED", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }}