package com.kippz.jenny.firstgen;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kippz.jenny.firstgen.data.PokemonContract.PokemonEntry;

public class AttributesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final String LOG_TAG = AttributesActivity.class.getName();

    private static final int POKEMON_LOADER = 0;

    private Uri mCurrentUri;

    private TextView nameTextView;
    private TextView numberTextView;
    private TextView subtypeTextView;
    private TextView supertypeTextView;
    private TextView hpTextView;
    private TextView atkNameTextView;
    private TextView atkTextTextView;
    private TextView atkDamageTextView;
    private TextView atkName2TextView;
    private TextView atkText2TextView;
    private TextView atkDamage2TextView;
    private TextView type1TextView;
    private TextView type2TextView;
    private TextView rarityTextView;
    private TextView weakTypeTextView;
    private TextView weakValueTextView;
    private TextView cardTextView;
    private TextView abilityNameTextView;
    private TextView abilityTextTextView;
    private TextView resistTypeTextView;
    private TextView resistValueTextView;
    private View lineView1;
    private View lineView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attribute_item);

        Intent intent = getIntent();
        mCurrentUri = intent.getData();

        nameTextView = (TextView) findViewById(R.id.att_name);
        numberTextView = (TextView) findViewById(R.id.att_npn);
        subtypeTextView = (TextView) findViewById(R.id.att_subtype);
        supertypeTextView = (TextView) findViewById(R.id.att_supertype);
        hpTextView = (TextView) findViewById(R.id.att_hp);
        atkNameTextView = (TextView) findViewById(R.id.atk_name1);
        atkTextTextView = (TextView) findViewById(R.id.atk_text1);
        atkDamageTextView = (TextView) findViewById(R.id.atk_ad1);
        atkName2TextView = (TextView) findViewById(R.id.atk_name2);
        atkText2TextView = (TextView) findViewById(R.id.atk_text2);
        atkDamage2TextView = (TextView) findViewById(R.id.atk_ad2);
        type1TextView = (TextView) findViewById(R.id.type1);
        type2TextView = (TextView) findViewById(R.id.type2);
        rarityTextView = (TextView) findViewById(R.id.att_rarity);
        weakTypeTextView = (TextView) findViewById(R.id.weakType);
        weakValueTextView = (TextView) findViewById(R.id.weakValue);
        cardTextView = (TextView) findViewById(R.id.card_number2);
        abilityNameTextView = (TextView) findViewById(R.id.ability_name);
        abilityTextTextView = (TextView) findViewById(R.id.ability_text);
        resistTypeTextView = (TextView) findViewById(R.id.resistType);
        resistValueTextView = (TextView) findViewById(R.id.resistValue);
        lineView1 = (View) findViewById(R.id.line1);
        lineView2 = (View) findViewById(R.id.line2);

        getLoaderManager().initLoader(POKEMON_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                PokemonEntry._ID,
                PokemonEntry.COLUMN_POKE_NAME,
                PokemonEntry.COLUMN_POKE_NUMBER,
                PokemonEntry.COLUMN_SUPERTYPE,
                PokemonEntry.COLUMN_POKE_HP,
                PokemonEntry.COLUMN_ATTACK_NAME,
                PokemonEntry.COLUMN_ATTACK_TEXT,
                PokemonEntry.COLUMN_ATTACK_DAMAGE,
                PokemonEntry.COLUMN_ATTACK_NAME2,
                PokemonEntry.COLUMN_ATTACK_TEXT2,
                PokemonEntry.COLUMN_ATTACK_DAMAGE2,
                PokemonEntry.COLUMN_TYPE_1,
                PokemonEntry.COLUMN_TYPE_2,
                PokemonEntry.COLUMN_SUBTYPE,
                PokemonEntry.COLUMN_CARD_NUMBER,
                PokemonEntry.COLUMN_RARITY,
                PokemonEntry.COLUMN_WEAK_TYPE,
                PokemonEntry.COLUMN_WEAK_VALUE,
                PokemonEntry.COLUMN_ABILITY_NAME,
                PokemonEntry.COLUMN_ABILITY_TEXT,
                PokemonEntry.COLUMN_RESIST_VALUE,
                PokemonEntry.COLUMN_RESIST_TYPE
        };

        return new CursorLoader(this,
                mCurrentUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null && cursor.getCount() <1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_POKE_NAME);
            int numberColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_POKE_NUMBER);
            int supertypeColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_SUPERTYPE);
            int hpColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_POKE_HP);
            int atkNameColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_ATTACK_NAME);
            int atkTextColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_ATTACK_TEXT);
            int atkDmgColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_ATTACK_DAMAGE);
            int atkName2ColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_ATTACK_NAME2);
            int atkText2ColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_ATTACK_TEXT2);
            int atkDmg2ColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_ATTACK_DAMAGE2);
            int subtypeColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_SUBTYPE);
            int type1ColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_TYPE_1);
            int type2ColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_TYPE_2);
            int rarityColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_RARITY);
            int weakTypeColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_WEAK_TYPE);
            int weakValueColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_WEAK_VALUE);
            int cardColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_CARD_NUMBER);
            int abilityNameColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_ABILITY_NAME);
            int abilityTextColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_ABILITY_TEXT);
            int resistTypeColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_RESIST_TYPE);
            int resistValueColumnIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_RESIST_VALUE);

            String name = cursor.getString(nameColumnIndex);
            String number = cursor.getString(numberColumnIndex);
            String supertype = cursor.getString(supertypeColumnIndex);
            String hp = cursor.getString(hpColumnIndex);
            String atkName = cursor.getString(atkNameColumnIndex);
            String atkText = cursor.getString(atkTextColumnIndex);
            String atkDmg = cursor.getString(atkDmgColumnIndex);
            String atkName2 = cursor.getString(atkName2ColumnIndex);
            String atkText2 = cursor.getString(atkText2ColumnIndex);
            String atkDmg2 = cursor.getString(atkDmg2ColumnIndex);
            String subtype = cursor.getString(subtypeColumnIndex);
            String type1 = cursor.getString(type1ColumnIndex);
            String type2 = cursor.getString(type2ColumnIndex);
            String rarity = cursor.getString(rarityColumnIndex);
            String weakType = cursor.getString(weakTypeColumnIndex);
            String weakValue = cursor.getString(weakValueColumnIndex);
            String card = cursor.getString(cardColumnIndex);
            String abilityName = cursor.getString(abilityNameColumnIndex);
            String abilityText = cursor.getString(abilityTextColumnIndex);
            String resistType = cursor.getString(resistTypeColumnIndex);
            String resistValue = cursor.getString(resistValueColumnIndex);

            nameTextView.setText(name);

            if (number.equals("n")) {
                numberTextView.setVisibility(View.GONE);
            } else {
                numberTextView.setText(number);
            }

            supertypeTextView.setText("Supertype: " + supertype);

            if (hp.equals("n")) {
                hpTextView.setVisibility(View.GONE);
            } else {
                hpTextView.setText(hp + " HP");
            }

            if (atkName.equals("n")) {
                atkNameTextView.setVisibility(View.GONE);
                atkTextTextView.setVisibility(View.GONE);
                atkDamageTextView.setVisibility(View.GONE);
                atkName2TextView.setVisibility(View.GONE);
                atkText2TextView.setVisibility(View.GONE);
                atkDamage2TextView.setVisibility(View.GONE);
                lineView1.setVisibility(View.GONE);
                lineView2.setVisibility(View.GONE);
            } else {
                atkNameTextView.setText("Attack: " + atkName);
                atkTextTextView.setText(atkText);
                atkDamageTextView.setText("Damage: " + atkDmg);
            }

            if (atkName2.equals("n")) {
                atkName2TextView.setVisibility(View.GONE);
                atkText2TextView.setVisibility(View.GONE);
                atkDamage2TextView.setVisibility(View.GONE);
                lineView2.setVisibility(View.GONE);
            } else {
                atkName2TextView.setText("Attack: " + atkName2);
                atkText2TextView.setText(atkText2);
                atkDamage2TextView.setText("Damage: " + atkDmg2);
            }

            if (subtype.equals("")) {
                subtypeTextView.setText("Subtype: N/A");
            } else {
                subtypeTextView.setText("Subtype: " + subtype);
            }


            if (type1.equals("n")) {
                type1TextView.setText("N/A");
            } else {
                type1TextView.setText(type1);
            }

            if (type2.equals("n")) {
                type2TextView.setVisibility(View.GONE);
            } else {
                type2TextView.setText(", " + type2);
            }

            rarityTextView.setText("Rarity: " + rarity);

            if (!supertype.equals("Pokémon")) {
                weakTypeTextView.setVisibility(View.GONE);
                weakValueTextView.setVisibility(View.GONE);
            } else if (weakValue.equals("n")) {
                weakValueTextView.setVisibility(View.GONE);
                weakTypeTextView.setText("Weakness: " + weakType);
            } else {
                weakTypeTextView.setText("Weakness: " + weakType);
                weakValueTextView.setText(weakValue);
            }

            cardTextView.setText("Card number: " + card);

            if (abilityName.equals("n")) {
                abilityNameTextView.setText("Ability: None");
            } else {
                abilityNameTextView.setText("Ability: " + abilityName);
            }

            if (abilityText.equals("n")) {
                abilityTextTextView.setVisibility(View.GONE);
            } else {
                abilityTextTextView.setText(abilityText);
            }

            if (!supertype.equals("Pokémon")) {
                resistTypeTextView.setVisibility(View.GONE);
                resistValueTextView.setVisibility(View.GONE);
            } else if (resistValue.equals("n")) {
                resistTypeTextView.setText("Resistance: " + resistType);
                resistValueTextView.setVisibility(View.GONE);
            } else {
                resistTypeTextView.setText("Resistance: " + resistType);
                resistValueTextView.setText(resistValue);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        nameTextView.setText("");
        numberTextView.setText("");
        supertypeTextView.setText("");
        hpTextView.setText("");
        atkNameTextView.setText("");
        atkTextTextView.setText("");
        atkDamageTextView.setText("");
        atkName2TextView.setText("");
        atkText2TextView.setText("");
        atkDamage2TextView.setText("");
        subtypeTextView.setText("");
        type1TextView.setText("");
        type2TextView.setText("");
        rarityTextView.setText("");
        weakTypeTextView.setText("");
        weakValueTextView.setText("");
        cardTextView.setText("");
        abilityNameTextView.setText("");
        abilityTextTextView.setText("");
        resistTypeTextView.setText("");
        resistValueTextView.setText("");
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
