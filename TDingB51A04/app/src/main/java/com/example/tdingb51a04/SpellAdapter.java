package com.example.tdingb51a04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpellAdapter  extends BaseAdapter {
    Context context;
    ArrayList<Spell> spells;
    LayoutInflater inflter;
    public SpellAdapter(Context applicationContext, ArrayList<Spell> spells) {
        this.context = applicationContext;
        this.spells = spells;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return spells.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spell_list_layout, null); // inflate the layout
        TextView txtSpellName = view.findViewById(R.id.txtSpellNameList);
        TextView txtSpellLevel = view.findViewById(R.id.txtSpellLevelList);
        TextView txtSpellClass = view.findViewById(R.id.txtClassList);
        TextView txtAction = view.findViewById(R.id.txtActionList);
        TextView txtConc = view.findViewById(R.id.txtConcentrationList);
        Spell currentSpell = spells.get(i);
        txtSpellName.setText(currentSpell.getName());
        String spellLevelName = "";
        if(spells.get(i).getSpellLevel().getSpellLevelID() == 1){
            spellLevelName += currentSpell.getSpellSchool().getSpellSchoolName()+" "+
                    currentSpell.getSpellLevel().getSpellLevelName();
        }
        else{
            spellLevelName += currentSpell.getSpellLevel().getSpellLevelName()+" "+
                    currentSpell.getSpellSchool().getSpellSchoolName();
        }
        if(currentSpell.isSpellIsRitual())
            spellLevelName+= " (Ritual)";
        txtSpellLevel.setText(spellLevelName);
        txtSpellClass.setText(currentSpell.getClassesAsName());
        txtAction.setText(currentSpell.getSpellCasting().getCastingTimeName());
        if(currentSpell.isSpellIsConcentration()){
            txtConc.setText("C");
        }
        else{
            txtConc.setText("");
        }
//        ImageView icon = (ImageView) view.findViewById(R.id.icon); // get the reference of ImageView
//        icon.setImageResource(logos[i]); // set logo images
        return view;
    }
}