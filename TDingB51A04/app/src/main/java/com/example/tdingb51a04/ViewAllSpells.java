package com.example.tdingb51a04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class ViewAllSpells extends AppCompatActivity {

    DBHandler dbController;
    SpellAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_spells);
        GridView lstView = (GridView) findViewById(R.id.spellView);
        try {
            if (dbController == null)
                dbController = new DBHandler(this);
            ArrayList<Spell> spellList = dbController.getAllSpells();
            myAdapter = new SpellAdapter(getApplicationContext(),spellList);
            lstView.setAdapter(myAdapter);
        } catch (Exception ex) {
            throw ex;
        }
        EditText searchbar = findViewById(R.id.txtSearch);
        searchbar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                ArrayList<Spell> spellList = dbController.searchSpells(s.toString());
                myAdapter = new SpellAdapter(getApplicationContext(),spellList);
                lstView.setAdapter(myAdapter);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Button btnMenu = findViewById(R.id.btnFilter);
        btnMenu.setOnClickListener((View v)->{
            Intent intent = new Intent(this, FilterActivity.class);
            startActivity(intent);
        });
    }
}
