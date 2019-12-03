package com.example.tdingb51a04;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    DBHandler dbController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("spellID");
        if (dbController == null)
            dbController = new DBHandler(this);
        Spell selectedSpell = dbController.getSpellById(id);
        TextView txtSpelltitle = findViewById(R.id.txtDetailsSpellTitle);
        txtSpelltitle.setText(selectedSpell.getName());
        TextView txtSpellLevel = findViewById(R.id.txtDetailsSpellLevel);
        String levelText = selectedSpell.isSpellIsRitual()?
                selectedSpell.getSpellLevel().getSpellLevelName()+" "+
                        selectedSpell.getSpellSchool().getSpellSchoolName() + " (Ritual)":
                selectedSpell.getSpellLevel().getSpellLevelName()+" "+
                        selectedSpell.getSpellSchool().getSpellSchoolName()
                ;
        txtSpellLevel.setText(levelText);

        TextView txtCasting = findViewById(R.id.txtDetailsSpellCasting);
        txtCasting.setText(" "+selectedSpell.getSpellCasting().getCastingTimeName());

        TextView txtRange = findViewById(R.id.txtDetailsSpellRange);
        txtRange.setText(" "+selectedSpell.getSpellRange().getRangeName());

        TextView txtComponent = findViewById(R.id.txtDetailsSpellComponent);
        String componentString = "";
        componentString += selectedSpell.isSpellIsVerbal() ? " V" : "";
        componentString += selectedSpell.isSpellIsSomatic() ?  " S" : "";
        componentString += selectedSpell.isSpellIsMaterial() ? " M("+
                selectedSpell.getSpellMaterial()+")" : "";
        txtComponent.setText(componentString);

        TextView txtDuration = findViewById(R.id.txtDetailsSpellDuration);
        String durationString = "";
        durationString += selectedSpell.isSpellIsConcentration() ? " Concentration, Up to "+
                selectedSpell.getSpellDuration().getDurationName()+"" :
                selectedSpell.getSpellDuration().getDurationName();
        txtDuration.setText(durationString);

        TextView txtDescription = findViewById(R.id.txtDetailsSpellDescription);
        String spellDesc = selectedSpell.getSpellDescription();
        spellDesc = spellDesc.replace("\\r\\n", System.getProperty("line.separator"));

        SpannableStringBuilder b = new SpannableStringBuilder();
        int startix = 0;
        int len =0;
        while(spellDesc.contains("<b>")){
            String[] spellDescParts = spellDesc.split("<b>");
            String spellLowerLv = spellDescParts[0];
            String[] spellDescPartsHighLv = spellDescParts[1].split("</b>");
            String spellHighLvHead = spellDescPartsHighLv[0];
            int tmp = spellDesc.indexOf("</b>");
            spellDesc = spellDesc.substring(tmp+4);
            b.append(spellLowerLv);
            len= spellHighLvHead.length();
            b.append(spellHighLvHead);
            startix = b.toString().length()-len;

            int x = (spellLowerLv+spellHighLvHead).length();
            b.setSpan(new StyleSpan(Typeface.BOLD), startix, startix + len,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        b.append(spellDesc);
        txtDescription.setText(b);
        TextView txtClass = findViewById(R.id.txtDetailsSpellClasses);
        txtClass.setText(" "+selectedSpell.getClassesAsName());

        TextView txtSource = findViewById(R.id.txtDetailsSpellSource);
        txtSource.setText(" "+selectedSpell.getSpellSource().getSourceName());
    }
}
