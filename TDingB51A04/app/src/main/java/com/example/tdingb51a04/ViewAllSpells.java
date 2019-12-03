package com.example.tdingb51a04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllSpells extends AppCompatActivity {

    DBHandler dbController;
    SpellAdapter myAdapter;
    ArrayList<Spell> spellList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_spells);
        GridView lstView = (GridView) findViewById(R.id.spellView);
        if (dbController == null)
            dbController = new DBHandler(this);
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (intent.hasExtra("classes") && intent.hasExtra("levels")
                && intent.hasExtra("sources") && intent.hasExtra("schools")
                && intent.hasExtra("effects") && intent.hasExtra("concentrations")
                && intent.hasExtra("isVerbal") && intent.hasExtra("isSomatic")
                && intent.hasExtra("isMaterial") && intent.hasExtra("rituals")) {

            boolean f = extras.getBoolean("isVerbal");
            spellList= dbController.filterSpells(intent.getIntegerArrayListExtra("classes")
                    , extras.getIntegerArrayList("levels")
                    , extras.getIntegerArrayList("sources")
                    , extras.getIntegerArrayList("schools")
                    , extras.getIntegerArrayList("effects")
                    , extras.getIntegerArrayList("concentrations")
                    , extras.getIntegerArrayList("rituals")
                    ,extras.getBoolean("isVerbal")
                    ,extras.getBoolean("isSomatic")
                    ,extras.getBoolean("isMaterial"));

        } else {
            spellList= dbController.getAllSpells();
        }
        myAdapter = new SpellAdapter(getApplicationContext(),spellList);
        lstView.setAdapter(myAdapter);
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
            Intent i = new Intent(this, FilterActivity.class);
            startActivity(i);
        });

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(getBaseContext(), DetailsActivity.class);
                i.putExtra("spellID",spellList.get(position).getSpellID());
                startActivity(i);

            }
        });
    }
}
