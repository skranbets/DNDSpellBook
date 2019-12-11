package com.example.tdingb51a04;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllSpells extends AppCompatActivity {

    DBHandler dbController;
    SpellAdapter myAdapter;
    ArrayList<Spell> spellList;
    GridView spellListView;
    EditText searchbar;
    ArrayList<Integer> classIds;
    ArrayList<Integer> levelIds;
    ArrayList<Integer> sourceIds;
    ArrayList<Integer> schoolIds;
    ArrayList<Integer> effectIds;
    ArrayList<Integer> filterOnConcentrations;
    ArrayList<Integer> filterOnRituals;
    boolean filterOnVerbal = false;
    boolean filterOnSomatic = false;
    boolean filterOnMaterial = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_spells);
        spellListView= (GridView) findViewById(R.id.spellView);
        resetFilter();
        if(getIntent().getExtras() == null)
            populateSpells(savedInstanceState);
        else
            populateSpells(getIntent().getExtras());
        searchbar = findViewById(R.id.txtSearch);
        searchbar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                ArrayList<Spell> searchedSpellList = searchSpells(s.toString(),spellList);
                if(searchedSpellList.size() == 0){
                    spellListView.setAdapter( new ArrayAdapter<String>(
                            getApplicationContext(), android.R.layout.simple_list_item_1,
                            new String[]{"No Spell Found"}));
                }
                else{
                    myAdapter = new SpellAdapter(getApplicationContext(),searchedSpellList);
                    spellListView.setAdapter(myAdapter);
                }
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
    private void resetFilter() {
        classIds = new ArrayList<Integer>();
        levelIds = new ArrayList<Integer>();
        sourceIds = new ArrayList<Integer>();
        schoolIds = new ArrayList<Integer>();
        effectIds = new ArrayList<Integer>();
        filterOnConcentrations = new ArrayList<Integer>();
        filterOnRituals = new ArrayList<Integer>();
//        filterOnConcentration = false;
//        filterOnRitual = false;
        filterOnVerbal = false;
        filterOnSomatic = false;
        filterOnMaterial = false;
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
            classIds = extras.getIntegerArrayList("classes");
            levelIds = extras.getIntegerArrayList("levels");
            sourceIds = extras.getIntegerArrayList("sources");
            schoolIds = extras.getIntegerArrayList("schools");
            effectIds = extras.getIntegerArrayList("effects");
            filterOnConcentrations = extras.getIntegerArrayList("concentrations");
            filterOnRituals = extras.getIntegerArrayList("rituals");
//        filterOnConcentration = false;
//        filterOnRitual = false;
            filterOnVerbal = extras.getBoolean("isVerbal");
            filterOnSomatic = extras.getBoolean("isSomatic");
            filterOnMaterial = extras.getBoolean("isMaterial");

        }
        spellList= dbController.filterSpells(classIds,levelIds,sourceIds,schoolIds,effectIds,
                filterOnConcentrations,filterOnRituals,filterOnVerbal,filterOnSomatic,
                filterOnMaterial);
        if(spellList.size() == 0){
            spellListView.setAdapter( new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1, new String[]{"No Spell Found"}));
        }
        else{
            myAdapter = new SpellAdapter(getApplicationContext(),spellList);
            spellListView.setAdapter(myAdapter);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("classes", classIds);
        outState.putIntegerArrayList("levels", levelIds);
        outState.putIntegerArrayList("sources", sourceIds);
        outState.putIntegerArrayList("schools", schoolIds);
        outState.putIntegerArrayList("effects", effectIds);
        outState.putIntegerArrayList("concentrations", filterOnConcentrations);
        outState.putIntegerArrayList("rituals", filterOnRituals);
        outState.putBoolean("isVerbal", filterOnVerbal);
        outState.putBoolean("isSomatic", filterOnSomatic);
        outState.putBoolean("isMaterial", filterOnMaterial);
    }
}
