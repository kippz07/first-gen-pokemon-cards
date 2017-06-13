package com.kippz.jenny.firstgen.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kippz.jenny.firstgen.data.PokemonContract.PokemonEntry;

import static com.kippz.jenny.firstgen.data.PokemonContract.PokemonEntry.TABLE_NAME;

/**
 * Created by Jenny on 10/10/2016.
 */

public class PokemonDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = PokemonDbHelper.class.getSimpleName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pokemon.db";

    public PokemonDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_POKE_TABLE = " CREATE TABLE " + TABLE_NAME + "("
                + PokemonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PokemonEntry.COLUMN_POKE_NAME + " TEXT NOT NULL, "
                + PokemonEntry.COLUMN_POKE_NUMBER + " INTEGER, "
                + PokemonEntry.COLUMN_POKE_HP + " TEXT, "
                + PokemonEntry.COLUMN_ATTACK_NAME + " TEXT, "
                + PokemonEntry.COLUMN_ATTACK_TEXT + " TEXT, "
                + PokemonEntry.COLUMN_ATTACK_DAMAGE + " TEXT, "
                + PokemonEntry.COLUMN_ATTACK_NAME2 + " TEXT, "
                + PokemonEntry.COLUMN_ATTACK_TEXT2 + " TEXT, "
                + PokemonEntry.COLUMN_ATTACK_DAMAGE2 + " TEXT, "
                + PokemonEntry.COLUMN_TYPE_1 + " TEXT, "
                + PokemonEntry.COLUMN_TYPE_2 + " TEXT, "
                + PokemonEntry.COLUMN_SUBTYPE + " TEXT, "
                + PokemonEntry.COLUMN_SUPERTYPE + " TEXT, "
                + PokemonEntry.COLUMN_SET_CODE + " TEXT, "
                + PokemonEntry.COLUMN_CARD_NUMBER+ " TEXT, "
                + PokemonEntry.COLUMN_RARITY + " TEXT, "
                + PokemonEntry.COLUMN_WEAK_TYPE + " TEXT, "
                + PokemonEntry.COLUMN_WEAK_VALUE + " TEXT, "
                + PokemonEntry.COLUMN_ABILITY_NAME + " TEXT, "
                + PokemonEntry.COLUMN_ABILITY_TEXT + " TEXT, "
                + PokemonEntry.COLUMN_RESIST_TYPE + " TEXT, "
                + PokemonEntry.COLUMN_RESIST_VALUE + " TEXT);";
        db.execSQL(SQL_CREATE_POKE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
