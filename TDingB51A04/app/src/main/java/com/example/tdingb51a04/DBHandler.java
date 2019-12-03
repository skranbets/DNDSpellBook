package com.example.tdingb51a04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private Context mCtx; //<-- declare a Context reference
    private static final String DATABASE_NAME = "DnDSpells.db";


    private static final String SPELL_TABLE_NAME = "Spell";
    private static final String SPELL_COLUMN_ID = "SpellID";
    private static final String SPELL_COLUMN_NAME = "SpellName";
    private static final String SPELL_COLUMN_SPELLLEVELID = "SpellLevelID";
    private static final String SPELL_COLUMN_DESCRIPTION = "SpellDescription";
    private static final String SPELL_COLUMN_DURATIONID = "SpellDurationID";
    private static final String SPELL_COLUMN_ATTACKID = "SpellAttackID";
    private static final String SPELL_COLUMN_DAMAGEID = "SpellDamageID";
    private static final String SPELL_COLUMN_RANGEID = "SpellRangeID";
    private static final String SPELL_COLUMN_SCHOOLID = "SpellSchoolID";
    private static final String SPELL_COLUMN_SOURCEID = "SpellSourceID";
    private static final String SPELL_COLUMN_CASTINGID = "SpellCastingID";
    private static final String SPELL_COLUMN_VERBAL = "SpellIsVerbal";
    private static final String SPELL_COLUMN_SOMATIC = "SpellIsSomatic";
    private static final String SPELL_COLUMN_MATERIAL = "SpellIsMaterial";
    private static final String SPELL_COLUMN_MATERIALNAME = "SpellMaterial";
    private static final String SPELL_COLUMN_CONCENTRATION = "SpellIsConcentration";
    private static final String SPELL_COLUMN_RITUAL = "SpellIsRitual";

    private static final String CLASS_TABLE_NAME = "PlayerClass";
    private static final String CLASS_COLUMN_ID = "PlayerClassID";
    private static final String CLASS_COLUMN_NAME = "PlayerClassName";

    private static final String LEVEL_TABLE_NAME = "SpellLevel";
    private static final String LEVEL_COLUMN_ID = "SpellLevelID";
    private static final String LEVEL_COLUMN_NAME = "SpellLevelName";

    private static final String SCHOOL_TABLE_NAME = "SpellSchool";
    private static final String SCHOOL_COLUMN_ID = "SpellSchoolID";
    private static final String SCHOOL_COLUMN_NAME = "SpellSchoolName";

    private static final String SOURCE_TABLE_NAME = "Source";
    private static final String SOURCE_COLUMN_ID = "SourceID";
    private static final String SOURCE_COLUMN_NAME = "SourceName";

    private static final String DURATION_TABLE_NAME = "Duration";
    private static final String DURATION_COLUMN_ID = "DurationID";
    private static final String DURATION_COLUMN_NAME = "DurationName";

    private static final String CASTING_TABLE_NAME = "CastingTime";
    private static final String CASTING_COLUMN_ID = "CastingTimeID";
    private static final String CASTING_COLUMN_NAME = "CastingTimeName";

    private static final String ATTACK_TABLE_NAME = "Attack";
    private static final String ATTACK_COLUMN_ID = "AttackID";
    private static final String ATTACK_COLUMN_NAME = "AttackName";

    private static final String DAMAGE_TABLE_NAME = "Damage";
    private static final String DAMAGE_COLUMN_ID = "DamageID";
    private static final String DAMAGE_COLUMN_NAME = "DamageName";

    private static final String RANGE_TABLE_NAME = "Range";
    private static final String RANGE_COLUMN_ID = "RangeID";
    private static final String RANGE_COLUMN_NAME = "RangeName";

    private static final String SPELLCLASS_TABLE_NAME = "SpellClass";
    private static final String SPELLCLASS_COLUMN_ID = "SpellClassID";
    private static final String SPELLCLASS_COLUMN_SPELLID = "SpellID";
    private static final String SPELLCLASS_COLUMN_CLASSID = "PlayerClassID";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mCtx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + ATTACK_TABLE_NAME +
                        " (" + ATTACK_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        ATTACK_COLUMN_NAME + " text NOT NULL)"
        );
        seedData(db, "attack.txt", ATTACK_TABLE_NAME, ATTACK_COLUMN_NAME);
        db.execSQL(
                "create table " + CASTING_TABLE_NAME +
                        " (" + CASTING_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        CASTING_COLUMN_NAME + " text NOT NULL)"
        );
        seedData(db, "casting.txt", CASTING_TABLE_NAME, CASTING_COLUMN_NAME);


        db.execSQL(
                "create table " + CLASS_TABLE_NAME +
                        " (" + CLASS_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        CLASS_COLUMN_NAME + " text NOT NULL)"
        );
        seedData(db, "class.txt", CLASS_TABLE_NAME, CLASS_COLUMN_NAME);

        db.execSQL(
                "create table " + DAMAGE_TABLE_NAME +
                        " (" + DAMAGE_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        DAMAGE_COLUMN_NAME + " text NOT NULL)"
        );
        seedData(db, "damage.txt", DAMAGE_TABLE_NAME, DAMAGE_COLUMN_NAME);
        db.execSQL(
                "create table " + DURATION_TABLE_NAME +
                        " (" + DURATION_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        DURATION_COLUMN_NAME + " text NOT NULL)"
        );
        seedData(db, "duration.txt", DURATION_TABLE_NAME, DURATION_COLUMN_NAME);

        db.execSQL(
                "create table " + LEVEL_TABLE_NAME +
                        " (" + LEVEL_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        LEVEL_COLUMN_NAME + " text NOT NULL)"
        );
        seedData(db, "level.txt", LEVEL_TABLE_NAME, LEVEL_COLUMN_NAME);


        db.execSQL(
                "create table " + SCHOOL_TABLE_NAME +
                        " (" + SCHOOL_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        SCHOOL_COLUMN_NAME + " text NOT NULL)"
        );
        seedData(db, "school.txt", SCHOOL_TABLE_NAME, SCHOOL_COLUMN_NAME);
        db.execSQL(
                "create table " + SOURCE_TABLE_NAME +
                        " (" + SOURCE_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        SOURCE_COLUMN_NAME + " text NOT NULL)"
        );
        seedData(db, "source.txt", SOURCE_TABLE_NAME, SOURCE_COLUMN_NAME);

        db.execSQL(
                "create table " + RANGE_TABLE_NAME +
                        " (" + RANGE_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        RANGE_COLUMN_NAME + " text NOT NULL)"
        );
        seedData(db, "range.txt", RANGE_TABLE_NAME, RANGE_COLUMN_NAME);

        db.execSQL(
                "create table " + SPELL_TABLE_NAME +
                        " (" + SPELL_COLUMN_ID + " integer primary key AUTOINCREMENT NOT NULL, " +
                        SPELL_COLUMN_NAME + " text NOT NULL," +
                        SPELL_COLUMN_DESCRIPTION + " text NOT NULL," +
                        SPELL_COLUMN_SCHOOLID + " INTEGER NOT NULL REFERENCES " +
                        SCHOOL_TABLE_NAME + "(" + SCHOOL_COLUMN_ID + ")," +
                        SPELL_COLUMN_ATTACKID + " INTEGER NOT NULL REFERENCES " +
                        ATTACK_TABLE_NAME + "(" + ATTACK_COLUMN_ID + ")," +
                        SPELL_COLUMN_CASTINGID + " INTEGER NOT NULL REFERENCES " +
                        CASTING_TABLE_NAME + "(" + CASTING_COLUMN_ID + ")," +
                        SPELL_COLUMN_DAMAGEID + " INTEGER NOT NULL REFERENCES " +
                        DAMAGE_TABLE_NAME + "(" + DAMAGE_COLUMN_ID + ")," +
                        SPELL_COLUMN_DURATIONID + " INTEGER NOT NULL REFERENCES " +
                        DURATION_TABLE_NAME + "(" + DURATION_COLUMN_ID + ")," +
                        SPELL_COLUMN_RANGEID + " INTEGER NOT NULL REFERENCES " +
                        RANGE_TABLE_NAME + "(" + RANGE_COLUMN_ID + ")," +
                        SPELL_COLUMN_SOURCEID + " INTEGER NOT NULL REFERENCES " +
                        SOURCE_TABLE_NAME + "(" + SOURCE_COLUMN_ID + ")," +

                        SPELL_COLUMN_SPELLLEVELID + " INTEGER NOT NULL REFERENCES " +
                        LEVEL_TABLE_NAME + "(" + LEVEL_COLUMN_ID + "), " +
                        SPELL_COLUMN_CONCENTRATION + " integer not null default 0," +
                        SPELL_COLUMN_RITUAL + " integer not null default 0," +
                        SPELL_COLUMN_VERBAL + " integer not null default 0," +
                        SPELL_COLUMN_SOMATIC + " integer not null default 0," +
                        SPELL_COLUMN_MATERIAL + " integer not null default 0," +
                        SPELL_COLUMN_MATERIALNAME + " Text" +
                        ")"
        );

        BufferedReader reader;
        try {
            final InputStream file = mCtx.getAssets().open("spell.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while (line != null) {
                String[] values = line.split("~");
                ContentValues contentValues = new ContentValues();
                contentValues.put(SPELL_COLUMN_NAME, values[0]);
                contentValues.put(SPELL_COLUMN_DESCRIPTION, values[1]);
                contentValues.put(SPELL_COLUMN_SCHOOLID, Integer.parseInt(values[2]));
                contentValues.put(SPELL_COLUMN_ATTACKID, Integer.parseInt(values[3]));
                contentValues.put(SPELL_COLUMN_CASTINGID, Integer.parseInt(values[4]));
                contentValues.put(SPELL_COLUMN_DAMAGEID, Integer.parseInt(values[5]));
                contentValues.put(SPELL_COLUMN_DURATIONID, Integer.parseInt(values[6]));
                contentValues.put(SPELL_COLUMN_RANGEID, Integer.parseInt(values[7]));
                contentValues.put(SPELL_COLUMN_SOURCEID, Integer.parseInt(values[8]));
                contentValues.put(SPELL_COLUMN_SPELLLEVELID, Integer.parseInt(values[9]) + 1);
                contentValues.put(SPELL_COLUMN_CONCENTRATION, Integer.parseInt(values[10]));
                contentValues.put(SPELL_COLUMN_RITUAL, Integer.parseInt(values[11]));
                contentValues.put(SPELL_COLUMN_VERBAL, Integer.parseInt(values[12]));
                contentValues.put(SPELL_COLUMN_SOMATIC, Integer.parseInt(values[13]));
                contentValues.put(SPELL_COLUMN_MATERIAL, Integer.parseInt(values[14]));
                contentValues.put(SPELL_COLUMN_MATERIALNAME, values[15]);
                db.insert(SPELL_TABLE_NAME, null, contentValues);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        db.execSQL(
                "create table " + SPELLCLASS_TABLE_NAME +
                        " (" + SPELLCLASS_COLUMN_ID +
                        " integer primary key AUTOINCREMENT NOT NULL, " +
                        SPELLCLASS_COLUMN_SPELLID + " INTEGER NOT NULL REFERENCES " +
                        SPELL_TABLE_NAME + "(" + SPELL_COLUMN_ID + ")," +
                        SPELLCLASS_COLUMN_CLASSID + " INTEGER NOT NULL REFERENCES " +
                        CLASS_TABLE_NAME + "(" + CLASS_COLUMN_ID + ")" +
                        ")"
        );
        try {
            final InputStream file = mCtx.getAssets().open("spellclass.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while (line != null) {
                String[] values = line.split("~");
                ContentValues contentValues = new ContentValues();
                contentValues.put(SPELLCLASS_COLUMN_SPELLID, Integer.parseInt(values[0]));
                contentValues.put(SPELLCLASS_COLUMN_CLASSID, Integer.parseInt(values[1]));
                db.insert(SPELLCLASS_TABLE_NAME, null, contentValues);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + CLASS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LEVEL_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SOURCE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RANGE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DURATION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAMAGE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CASTING_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SCHOOL_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ATTACK_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SPELL_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SPELLCLASS_TABLE_NAME);

        onCreate(db);
    }


    private void seedData(SQLiteDatabase db, String fileName, String table, String column) {
        BufferedReader reader;
        try {
            final InputStream file = mCtx.getAssets().open(fileName);
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while (line != null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(column, line);
                db.insert(table, null, contentValues);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Spell> getAllSpells() {
        ArrayList<Spell> spell_list = new ArrayList<Spell>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SPELL_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            Spell currentSpell = new Spell(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_ID)),
                    res.getString(res.getColumnIndex(SPELL_COLUMN_NAME)),
                    res.getString(res.getColumnIndex(SPELL_COLUMN_DESCRIPTION)),
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_VERBAL)) == 1,
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_SOMATIC)) == 1,
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_MATERIAL)) == 1,
                    res.getString(res.getColumnIndex(SPELL_COLUMN_MATERIALNAME)),
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_CONCENTRATION)) == 1,
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_RITUAL)) == 1);
            currentSpell.setSpellAttack(getSpellAttackById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_ATTACKID))));
            currentSpell.setSpellCasting(getCastingTimeById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_CASTINGID))));
            currentSpell.setSpellDamage(getDamageTypeById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_DAMAGEID))));
            currentSpell.setSpellDuration(getDurationById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_DURATIONID))));

            currentSpell.setClasses(getPlayerClassBySpellId(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_ID))
            ));
            currentSpell.setSpellRange(getRangeById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_RANGEID))));

            currentSpell.setSpellSchool(getSpellSchoolById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_SCHOOLID))));
            currentSpell.setSpellSource(getSourceById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_SOURCEID))));
            currentSpell.setSpellLevel(getSpellLevelById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_SPELLLEVELID))));
            spell_list.add(currentSpell);
            res.moveToNext();
        }
        res.close();
        return spell_list;
    }

    public Spell getSpellById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SPELL_TABLE_NAME + " where " +
                SPELL_COLUMN_ID + "=" + id + "", null);
        res.moveToFirst();
        Spell currentSpell = new Spell(
                res.getInt(res.getColumnIndex(SPELL_COLUMN_ID)),
                res.getString(res.getColumnIndex(SPELL_COLUMN_NAME)),
                res.getString(res.getColumnIndex(SPELL_COLUMN_DESCRIPTION)),
                res.getInt(res.getColumnIndex(SPELL_COLUMN_VERBAL)) == 1,
                res.getInt(res.getColumnIndex(SPELL_COLUMN_SOMATIC)) == 1,
                res.getInt(res.getColumnIndex(SPELL_COLUMN_MATERIAL)) == 1,
                res.getString(res.getColumnIndex(SPELL_COLUMN_MATERIALNAME)),
                res.getInt(res.getColumnIndex(SPELL_COLUMN_CONCENTRATION)) == 1,
                res.getInt(res.getColumnIndex(SPELL_COLUMN_RITUAL)) == 1);
        currentSpell.setSpellAttack(getSpellAttackById(
                res.getInt(res.getColumnIndex(SPELL_COLUMN_ATTACKID))));
        currentSpell.setSpellCasting(getCastingTimeById(
                res.getInt(res.getColumnIndex(SPELL_COLUMN_CASTINGID))));
        currentSpell.setSpellDamage(getDamageTypeById(
                res.getInt(res.getColumnIndex(SPELL_COLUMN_DAMAGEID))));
        currentSpell.setSpellDuration(getDurationById(
                res.getInt(res.getColumnIndex(SPELL_COLUMN_DURATIONID))));

        currentSpell.setClasses(getPlayerClassBySpellId(
                res.getInt(res.getColumnIndex(SPELL_COLUMN_ID))
        ));
        currentSpell.setSpellRange(getRangeById(
                res.getInt(res.getColumnIndex(SPELL_COLUMN_RANGEID))));

        currentSpell.setSpellSchool(getSpellSchoolById(
                res.getInt(res.getColumnIndex(SPELL_COLUMN_SCHOOLID))));
        currentSpell.setSpellSource(getSourceById(
                res.getInt(res.getColumnIndex(SPELL_COLUMN_SOURCEID))));
        currentSpell.setSpellLevel(getSpellLevelById(
                res.getInt(res.getColumnIndex(SPELL_COLUMN_SPELLLEVELID))));
        res.close();
        return currentSpell;
    }

    public ArrayList<CastingTime> getCastingTimes(){
        ArrayList<CastingTime> casting_time_list = new ArrayList<CastingTime>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SPELL_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            CastingTime ct = new CastingTime();
            ct.setCastingTimeID(res.getInt(res.getColumnIndex(CASTING_COLUMN_ID)));
            ct.setCastingTimeName(res.getString(res.getColumnIndex(CASTING_COLUMN_NAME)));
            casting_time_list.add(ct);
            res.moveToNext();
        }
        res.close();
        return casting_time_list;
    }

    public CastingTime getCastingTimeById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + CASTING_TABLE_NAME + " where " +
                CASTING_COLUMN_ID + "=" + id + "", null);
        res.moveToFirst();
        CastingTime ct = new CastingTime();
        ct.setCastingTimeID(res.getInt(res.getColumnIndex(CASTING_COLUMN_ID)));
        ct.setCastingTimeName(res.getString(res.getColumnIndex(CASTING_COLUMN_NAME)));
        return ct;
    }

    public ArrayList<PlayerClass> getPlayerClassBySpellId(int id) {
        ArrayList<PlayerClass> class_list = new ArrayList<PlayerClass>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SPELLCLASS_TABLE_NAME + " where " +
                SPELLCLASS_COLUMN_SPELLID + " = " + id + "", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            PlayerClass pc = getPlayerClassById(res.getInt(
                    res.getColumnIndex(SPELLCLASS_COLUMN_CLASSID)));
            class_list.add(pc);
            res.moveToNext();
        }
        res.close();
        return class_list;
    }

    public ArrayList<DamageType> getDamageTypes() {
        ArrayList<DamageType> damage_list = new ArrayList<DamageType>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DAMAGE_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            DamageType dt = new DamageType();
            dt.setDamageTypeID(res.getInt(res.getColumnIndex(DAMAGE_COLUMN_ID)));
            dt.setDamageTypeName(res.getString(res.getColumnIndex(DAMAGE_COLUMN_NAME)));
            damage_list.add(dt);
            res.moveToNext();
        }
        res.close();
        return damage_list;
    }

    public DamageType getDamageTypeById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DAMAGE_TABLE_NAME + " where " +
                DAMAGE_COLUMN_ID + "=" + id + "", null);
        res.moveToFirst();
        DamageType dt = new DamageType();
        dt.setDamageTypeID(res.getInt(res.getColumnIndex(DAMAGE_COLUMN_ID)));
        dt.setDamageTypeName(res.getString(res.getColumnIndex(DAMAGE_COLUMN_NAME)));
        return dt;
    }

    public ArrayList<Duration> getDurations() {
        ArrayList<Duration> duration_list = new ArrayList<Duration>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DURATION_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            Duration d = new Duration();
            d.setDurationID(res.getInt(res.getColumnIndex(DURATION_COLUMN_ID)));
            d.setDurationName(res.getString(res.getColumnIndex(DURATION_COLUMN_NAME)));
            duration_list.add(d);
            res.moveToNext();
        }
        res.close();
        return duration_list;
    }

    public Duration getDurationById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DURATION_TABLE_NAME + " where " +
                DURATION_COLUMN_ID + "=" + id + "", null);
        res.moveToFirst();
        Duration d = new Duration();
        d.setDurationID(res.getInt(res.getColumnIndex(DURATION_COLUMN_ID)));
        d.setDurationName(res.getString(res.getColumnIndex(DURATION_COLUMN_NAME)));
        return d;
    }


    public ArrayList<PlayerClass> getPlayerClasses(){
        ArrayList<PlayerClass> class_list = new ArrayList<PlayerClass>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + CLASS_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            PlayerClass currentClass = getPlayerClassById(
                    res.getInt(res.getColumnIndex(SPELLCLASS_COLUMN_CLASSID)));
            class_list.add(currentClass);
            res.moveToNext();
        }
        res.close();
        return class_list;
    }
    public PlayerClass getPlayerClassById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + CLASS_TABLE_NAME + " where " +
                CLASS_COLUMN_ID + "=" + id + "", null);
        res.moveToFirst();
        PlayerClass pc = new PlayerClass(
                res.getInt(res.getColumnIndex(CLASS_COLUMN_ID)),
                res.getString(res.getColumnIndex(CLASS_COLUMN_NAME))
        );
        return pc;
    }


    public ArrayList<Range> getRanges() {
        ArrayList<Range> range_list = new ArrayList<Range>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + RANGE_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            Range r = new Range();
            r.setRangeID(res.getInt(res.getColumnIndex(RANGE_COLUMN_ID)));
            r.setRangeName(res.getString(res.getColumnIndex(RANGE_COLUMN_NAME)));
            range_list.add(r);
            res.moveToNext();
        }
        res.close();
        return range_list;
    }
    public Range getRangeById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + RANGE_TABLE_NAME + " where " +
                RANGE_COLUMN_ID + "=" + id + "", null);
        res.moveToFirst();
        Range r = new Range();
        r.setRangeID(res.getInt(res.getColumnIndex(RANGE_COLUMN_ID)));
        r.setRangeName(res.getString(res.getColumnIndex(RANGE_COLUMN_NAME)));
        return r;
    }
    public ArrayList<Source> getSources() {
        ArrayList<Source> source_list = new ArrayList<Source>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SOURCE_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            Source source = new Source();
            source.setSourceID(res.getInt(res.getColumnIndex(SOURCE_COLUMN_ID)));
            source.setSourceName(res.getString(res.getColumnIndex(SOURCE_COLUMN_NAME)));
            source_list.add(source);
            res.moveToNext();
        }
        res.close();
        return source_list;
    }

    public Source getSourceById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SOURCE_TABLE_NAME + " where " +
                SOURCE_COLUMN_ID + "=" + id + "", null);
        res.moveToFirst();
        Source source = new Source();
        source.setSourceID(res.getInt(res.getColumnIndex(SOURCE_COLUMN_ID)));
        source.setSourceName(res.getString(res.getColumnIndex(SOURCE_COLUMN_NAME)));
        return source;
    }
    public ArrayList<SpellAttack> getSpellAttacks() {
        ArrayList<SpellAttack> attack_list = new ArrayList<SpellAttack>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + ATTACK_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            SpellAttack a = new SpellAttack();
            a.setSpellAttackID(res.getInt(res.getColumnIndex(ATTACK_COLUMN_ID)));
            a.setSpellAttackName(res.getString(res.getColumnIndex(ATTACK_COLUMN_NAME)));
            attack_list.add(a);
            res.moveToNext();
        }
        res.close();
        return attack_list;
    }

    public SpellAttack getSpellAttackById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + ATTACK_TABLE_NAME + " where " +
                ATTACK_COLUMN_ID + "=" + id + "", null);
        res.moveToFirst();
        SpellAttack a = new SpellAttack();
        a.setSpellAttackID(res.getInt(res.getColumnIndex(ATTACK_COLUMN_ID)));
        a.setSpellAttackName(res.getString(res.getColumnIndex(ATTACK_COLUMN_NAME)));
        return a;
    }
    public ArrayList<SpellLevel> getSpellLevels() {
        ArrayList<SpellLevel> level_list = new ArrayList<SpellLevel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + LEVEL_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            SpellLevel sl = new SpellLevel();
            sl.setSpellLevelID(res.getInt(res.getColumnIndex(LEVEL_COLUMN_ID)));
            sl.setSpellLevelName(res.getString(res.getColumnIndex(LEVEL_COLUMN_NAME)));
            level_list.add(sl);
            res.moveToNext();
        }
        res.close();
        return level_list;
    }

    public SpellLevel getSpellLevelById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + LEVEL_TABLE_NAME + " where " +
                LEVEL_COLUMN_ID + "=" + id + "", null);
        res.moveToFirst();
        SpellLevel sl = new SpellLevel();
        sl.setSpellLevelID(res.getInt(res.getColumnIndex(LEVEL_COLUMN_ID)));
        sl.setSpellLevelName(res.getString(res.getColumnIndex(LEVEL_COLUMN_NAME)));
        return sl;
    }
    public ArrayList<SpellSchool> getSpellSchools() {
        ArrayList<SpellSchool> school_list = new ArrayList<SpellSchool>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SCHOOL_TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            SpellSchool ss = new SpellSchool();
            ss.setSpellSchoolID(res.getInt(res.getColumnIndex(SCHOOL_COLUMN_ID)));
            ss.setSpellSchoolName(res.getString(res.getColumnIndex(SCHOOL_COLUMN_NAME)));
            school_list.add(ss);
            res.moveToNext();
        }
        res.close();
        return school_list;
    }

    public SpellSchool getSpellSchoolById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SCHOOL_TABLE_NAME + " where " +
                SCHOOL_COLUMN_ID + "=" + id + "", null);
        res.moveToFirst();
        SpellSchool ss = new SpellSchool();
        ss.setSpellSchoolID(res.getInt(res.getColumnIndex(SCHOOL_COLUMN_ID)));
        ss.setSpellSchoolName(res.getString(res.getColumnIndex(SCHOOL_COLUMN_NAME)));
        return ss;
    }

    public ArrayList<Spell> searchSpells(String toString) {
        ArrayList<Spell> spell_list = new ArrayList<Spell>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + SPELL_TABLE_NAME + " where " +
                SPELL_COLUMN_NAME + " LIKE  '%" + toString + "%'", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            Spell currentSpell = new Spell(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_ID)),
                    res.getString(res.getColumnIndex(SPELL_COLUMN_NAME)),
                    res.getString(res.getColumnIndex(SPELL_COLUMN_DESCRIPTION)),
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_VERBAL)) == 1,
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_SOMATIC)) == 1,
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_MATERIAL)) == 1,
                    res.getString(res.getColumnIndex(SPELL_COLUMN_MATERIALNAME)),
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_CONCENTRATION)) == 1,
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_RITUAL)) == 1);
            currentSpell.setSpellAttack(getSpellAttackById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_ATTACKID))));
            currentSpell.setSpellCasting(getCastingTimeById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_CASTINGID))));
            currentSpell.setSpellDamage(getDamageTypeById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_DAMAGEID))));
            currentSpell.setSpellDuration(getDurationById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_DURATIONID))));

            currentSpell.setClasses(getPlayerClassBySpellId(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_ID))
            ));
            currentSpell.setSpellRange(getRangeById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_RANGEID))));

            currentSpell.setSpellSchool(getSpellSchoolById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_SCHOOLID))));
            currentSpell.setSpellSource(getSourceById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_SOURCEID))));
            currentSpell.setSpellLevel(getSpellLevelById(
                    res.getInt(res.getColumnIndex(SPELL_COLUMN_SPELLLEVELID))));
            spell_list.add(currentSpell);
            res.moveToNext();
        }
        res.close();
        return spell_list;
    }
}