package com.example.tdingb51a04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout menuview = findViewById(R.id.layoutMenuView);
        LinearLayout menuhelp = findViewById(R.id.layoutMenuHelp);
        LinearLayout menuAbout = findViewById(R.id.layoutMenuAbout);
        menuview.setOnClickListener((View v)->{
            Intent intent = new Intent(this, ViewAllSpells.class);
            startActivity(intent);
        });
        menuhelp.setOnClickListener((View v)->{
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        });
        menuAbout.setOnClickListener((View v)->{
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        });
    }

}
