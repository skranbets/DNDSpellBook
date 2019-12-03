package com.example.tdingb51a04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterActivity extends AppCompatActivity {
    FilterListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    DBHandler dbController;
    List<Integer> classIds;
    List<Integer> levelIds;
    List<Integer> sourceIds;
    List<Integer> schoolIds;
    List<Integer> effectIds;
    boolean filterOnConcentration;
    boolean filterOnRitual;
    boolean filterOnVerbal;
    boolean filterOnSomatic;
    boolean filterOnMaterial;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // get the listview
        expListView = findViewById(R.id.lvFilter);
        if (dbController == null)
            dbController = new DBHandler(this);

        // preparing list data
        prepareListData();

        listAdapter = new FilterListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        resetFilter();
        expListView.setOnChildClickListener(new ExpandableListView.
                OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                switch (groupPosition){
                    case(0):
                        if(classIds.contains(childPosition))
                            classIds.remove(childPosition);
                        else
                            classIds.add(childPosition);
                        break;
                    case(1):
                        if(levelIds.contains(childPosition))
                            levelIds.remove(childPosition);
                        else
                            levelIds.add(childPosition);
                        break;
                    case(2):

                        if(sourceIds.contains(childPosition))
                            sourceIds.remove(childPosition);
                        else
                            sourceIds.add(childPosition);
                        break;
                    case(3):

                        if(schoolIds.contains(childPosition))
                            schoolIds.remove(childPosition);
                        else
                            schoolIds.add(childPosition);
                        break;
                    case(4):
                        if(filterOnConcentration)
                            filterOnConcentration = !(childPosition == 0);
                        else
                            filterOnConcentration = childPosition == 0;
                        break;
                    case(5):
                        if(filterOnRitual)
                            filterOnRitual = !(childPosition == 0);
                        else
                            filterOnRitual = childPosition == 0;
                        break;
                    case(6):
                        switch (childPosition){
                            case(0):
                                if(filterOnVerbal)
                                    filterOnVerbal = false;
                                else
                                    filterOnVerbal = true;
                                break;
                            case(1):
                                if(filterOnSomatic)
                                    filterOnSomatic = false;
                                else
                                    filterOnSomatic = true;
                                break;
                            case(2):
                                if(filterOnMaterial)
                                    filterOnMaterial = false;
                                else
                                    filterOnMaterial = true;
                        }
                        break;


                    case(7):
                        if(effectIds.contains(childPosition))
                            effectIds.remove(childPosition);
                        else
                            effectIds.add(childPosition);
                        break;
                }
                return false;
            }
        });
    }

    private void resetFilter() {
        classIds = new ArrayList<Integer>();
        levelIds = new ArrayList<Integer>();
        sourceIds = new ArrayList<Integer>();
        schoolIds = new ArrayList<Integer>();
        effectIds = new ArrayList<Integer>();
        filterOnConcentration = false;
        filterOnRitual = false;
        filterOnVerbal = false;
        filterOnSomatic = false;
        filterOnMaterial = false;
    }


    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Class");
        listDataHeader.add("Spell Level");
        listDataHeader.add("Source");
        listDataHeader.add("School");
        listDataHeader.add("Concentration");
        listDataHeader.add("Ritual");
        listDataHeader.add("Component");
        listDataHeader.add("Damage/Effect");

        // Adding child data
        List<String> classNameList = new ArrayList<String>();
        List<PlayerClass> classList = dbController.getPlayerClasses();
        for(PlayerClass pc : classList){
            classNameList.add(pc.getPlayerClassName());
        }
        List<String> levelNameList = new ArrayList<String>();
        List<SpellLevel> lvList = dbController.getSpellLevels();
        for(SpellLevel lv : lvList){
            levelNameList.add(lv.getSpellLevelName());
        }
        List<String> sourceNameList = new ArrayList<String>();
        List<Source> sourceList = dbController.getSources();
        for(Source s: sourceList){
            sourceNameList.add(s.getSourceName());
        }
        List<String> schoolNameList = new ArrayList<String>();
        List<SpellSchool> schoolList = dbController.getSpellSchools();
        for(SpellSchool ss : schoolList){
            schoolNameList.add(ss.getSpellSchoolName());
        }
        List<String> concentrationNameList = new ArrayList<String>();
        concentrationNameList.add("Yes");
        concentrationNameList.add("No");
        List<String> ritualNameList = new ArrayList<String>();
        ritualNameList.add("Yes");
        ritualNameList.add("No");
        List<String> componentNameList = new ArrayList<String>();
        componentNameList.add("Verbal");
        componentNameList.add("Somatic");
        componentNameList.add("Material");
        List<String> effectNameList = new ArrayList<String>();
        List<DamageType> effectList = dbController.getDamageTypes();
        for(DamageType dt: effectList){
            effectNameList.add(dt.getDamageTypeName());
        }

        listDataChild.put(listDataHeader.get(0), classNameList); // Header, Child data
        listDataChild.put(listDataHeader.get(1), levelNameList);
        listDataChild.put(listDataHeader.get(2), sourceNameList);
        listDataChild.put(listDataHeader.get(3), schoolNameList);
        listDataChild.put(listDataHeader.get(4), concentrationNameList);
        listDataChild.put(listDataHeader.get(5), ritualNameList);
        listDataChild.put(listDataHeader.get(6), componentNameList);
        listDataChild.put(listDataHeader.get(7), effectNameList);
    }
}