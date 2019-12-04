package com.example.tdingb51a04;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
    GridView spellListView;
    EditText searchbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_spells);
        spellListView= (GridView) findViewById(R.id.spellView);

        populateSpells(getIntent().getExtras());
        searchbar = findViewById(R.id.txtSearch);
        searchbar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                ArrayList<Spell> searchedSpellList = searchSpells(s.toString(),spellList);
                myAdapter = new SpellAdapter(getApplicationContext(),searchedSpellList);
                spellListView.setAdapter(myAdapter);
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
            startActivityForResult(i,1000);
        });

        spellListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent i = new Intent(getBaseContext(), DetailsActivity.class);
                i.putExtra("spellID",spellList.get(position).getSpellID());
                startActivity(i);
        }
        });
    }

    private ArrayList<Spell> searchSpells(String query, ArrayList<Spell> spellList) {
        ArrayList<Spell> foundSpells = new ArrayList<Spell>();
        for(Spell s : spellList){
            if(s.getName().toUpperCase().contains(query.toUpperCase()))
                foundSpells.add(s);
        }
        return foundSpells;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("Hello");
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            Bundle filterBundle = data.getExtras();
            spellListView.setAdapter(null);
            searchbar.setText("");
            populateSpells(filterBundle);
            // do something with B's return values
        }
    }

    private void populateSpells(Bundle extras) {
        if (dbController == null)
            dbController = new DBHandler(this);
        if (extras!= null && extras.containsKey("classes") && extras.containsKey("levels")
                && extras.containsKey("sources") && extras.containsKey("schools")
                && extras.containsKey("effects") && extras.containsKey("concentrations")
                && extras.containsKey("isVerbal") && extras.containsKey("isSomatic")
                && extras.containsKey("isMaterial") && extras.containsKey("rituals")) {
            spellList= dbController.filterSpells(extras.getIntegerArrayList("classes")
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
        spellListView.setAdapter(myAdapter);
    }


}
