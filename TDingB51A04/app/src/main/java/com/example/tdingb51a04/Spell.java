package com.example.tdingb51a04;

import java.util.ArrayList;

public class Spell {
    private int spellID;
    private String name;
    private SpellLevel spellLevel;
    private String spellDescription;
    private Duration spellDuration;
    private SpellAttack spellAttack;
    private DamageType spellDamage;
    private Range spellRange;
    private SpellSchool spellSchool;
    private Source spellSource;
    private CastingTime spellCasting;
    private boolean spellIsVerbal;
    private boolean spellIsSomatic;
    private boolean spellIsMaterial;
    private String spellMaterial;
    private boolean spellIsConcentration;
    private boolean spellIsRitual;
    private ArrayList<PlayerClass> spellClasses;


    public Spell(){

    }
    public Spell(int spellID, String name,String spellDescription, boolean spellIsVerbal,
                 boolean spellIsSomatic, boolean spellIsMaterial, String spellMaterial,
                 boolean spellIsConcentration, boolean spellIsRitual){
        this.spellID = spellID;
        this.name = name;
        this.spellDescription = spellDescription;
        this.spellIsVerbal = spellIsVerbal;
        this.spellIsSomatic = spellIsSomatic;
        this.spellIsMaterial = spellIsMaterial;
        this.spellMaterial = spellMaterial;
        this.spellIsConcentration = spellIsConcentration;
        this.spellIsRitual = spellIsRitual;

    }
    public Spell(int spellID, String name, SpellLevel spellLevel, String spellDescription,
                 Duration spellDuration, SpellAttack spellAttack, DamageType spellDamage,
                 Range spellRange,SpellSchool spellSchool, Source spellSource,
                 CastingTime spellCasting, boolean spellIsVerbal,
                 boolean spellIsSomatic, boolean spellIsMaterial, String spellMaterial,
                 boolean spellIsConcentration, boolean spellIsRitual,
                 ArrayList<PlayerClass> spellClasses) {
        this.spellID = spellID;
        this.name = name;
        this.spellLevel = spellLevel;
        this.spellDescription = spellDescription;
        this.spellDuration = spellDuration;
        this.spellAttack = spellAttack;
        this.spellDamage = spellDamage;
        this.spellRange = spellRange;
        this.spellSchool = spellSchool;
        this.spellSource = spellSource;
        this.spellCasting = spellCasting;
        this.spellIsVerbal = spellIsVerbal;
        this.spellIsSomatic = spellIsSomatic;
        this.spellIsMaterial = spellIsMaterial;
        this.spellMaterial = spellMaterial;
        this.spellIsConcentration = spellIsConcentration;
        this.spellIsRitual = spellIsRitual;
        this.spellClasses = spellClasses;
    }

    public int getSpellID() {
        return spellID;
    }

    public void setSpellID(int spellID) {
        this.spellID = spellID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpellLevel getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(SpellLevel spellLevel) {
        this.spellLevel = spellLevel;
    }

    public String getSpellDescription() {
        return spellDescription;
    }

    public void setSpellDescription(String spellDescription) {
        this.spellDescription = spellDescription;
    }

    public Duration getSpellDuration() {
        return spellDuration;
    }

    public void setSpellDuration(Duration spellDuration) {
        this.spellDuration = spellDuration;
    }

    public SpellAttack getSpellAttack() {
        return spellAttack;
    }

    public void setSpellAttack(SpellAttack spellAttack) {
        this.spellAttack = spellAttack;
    }

    public DamageType getSpellDamage() {
        return spellDamage;
    }

    public void setSpellDamage(DamageType spellDamage) {
        this.spellDamage = spellDamage;
    }

    public Range getSpellRange() {
        return spellRange;
    }

    public void setSpellRange(Range spellRange) {
        this.spellRange = spellRange;
    }

    public SpellSchool getSpellSchool() {
        return spellSchool;
    }

    public void setSpellSchool(SpellSchool spellSchool) {
        this.spellSchool = spellSchool;
    }

    public Source getSpellSource() {
        return spellSource;
    }

    public void setSpellSource(Source spellSource) {
        this.spellSource = spellSource;
    }

    public CastingTime getSpellCasting() {
        return spellCasting;
    }

    public void setSpellCasting(CastingTime spellCasting) {
        this.spellCasting = spellCasting;
    }

    public boolean isSpellIsVerbal() {
        return spellIsVerbal;
    }

    public void setSpellIsVerbal(boolean spellIsVerbal) {
        this.spellIsVerbal = spellIsVerbal;
    }

    public boolean isSpellIsSomatic() {
        return spellIsSomatic;
    }

    public void setSpellIsSomatic(boolean spellIsSomatic) {
        this.spellIsSomatic = spellIsSomatic;
    }

    public boolean isSpellIsMaterial() {
        return spellIsMaterial;
    }

    public void setSpellIsMaterial(boolean spellIsMaterial) {
        this.spellIsMaterial = spellIsMaterial;
    }

    public String getSpellMaterial() {
        return spellMaterial;
    }

    public void setSpellMaterial(String spellMaterial) {
        this.spellMaterial = spellMaterial;
    }

    public boolean isSpellIsConcentration() {
        return spellIsConcentration;
    }

    public void setSpellIsConcentration(boolean spellIsConcentration) {
        this.spellIsConcentration = spellIsConcentration;
    }

    public boolean isSpellIsRitual() {
        return spellIsRitual;
    }

    public void setSpellIsRitual(boolean spellIsRitual) {
        this.spellIsRitual = spellIsRitual;
    }
    public ArrayList<PlayerClass> getClasses() {
        return spellClasses;
    }

    public void setClasses(ArrayList<PlayerClass> classes) {
        this.spellClasses = classes;
    }
    public String getClassesAsName(){
        String returnedClasses = "";
        for(PlayerClass c: this.spellClasses){
            returnedClasses+= c.getPlayerClassName()+", ";
        }
        return returnedClasses.substring(0, returnedClasses.length() - 2);
    }
}
