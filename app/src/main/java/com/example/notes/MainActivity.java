package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
static ArrayList<String> notes;
static ArrayAdapter<String>adapter;
SharedPreferences s;
int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s=getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        notes=new ArrayList<>();
        Set<String> w=s.getStringSet("notes",null);
         if(w==null){
             notes.add("EXAMPLE NOTES");
         }
         else{
             notes= new ArrayList(w);}
        ListView l=findViewById(R.id.listview);
        adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,notes);
        l.setAdapter(adapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(MainActivity.this,note.class);
                i.putExtra("pos",position);
                i.putExtra("note",notes.get(position));
                startActivity(i);
            }
        });

        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View v, int index, long arg3) {
                pos=index;
                AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);

                b.setTitle("ARE YOU SURE?");
                b.setMessage("DO YOU REALLY WANT TO DELETE THIS NOTE?");
                b.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        adapter.remove(notes.get(pos));
                        //notes.remove(index);
                        adapter.notifyDataSetChanged();
                        HashSet<String> h=new HashSet<>(MainActivity.notes);
                        s.edit().putStringSet("notes",h).apply();
                        Log.i("size",String.valueOf(notes.size()));

                    }
                });
                b.setNegativeButton("NO",null);
                AlertDialog dialoge=b.create();
                dialoge.show();
              return true;
            }
        });
        ImageButton p=findViewById(R.id.imageButton);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.add("TYPE HERE");
                adapter.notifyDataSetChanged();

            }
        });
    }

    }



