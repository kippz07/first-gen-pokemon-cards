package com.kippz.jenny.firstgen;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.kippz.jenny.firstgen.data.PokemonContract;

/**
 * Created by Jenny on 15/10/2016.
 */

public class PokemonCursorAdapter extends CursorAdapter {
    public PokemonCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.pokemon_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameView = (TextView) view.findViewById(R.id.poke_name);
        TextView numberView = (TextView) view.findViewById(R.id.poke_number);
        TextView cardView = (TextView) view.findViewById(R.id.card_number);

        int nameColumnIndex = cursor.getColumnIndex(PokemonContract.PokemonEntry.COLUMN_POKE_NAME);
        int numberColumnIndex = cursor.getColumnIndex(PokemonContract.PokemonEntry.COLUMN_POKE_NUMBER);
        int cardColumnIndex = cursor.getColumnIndex(PokemonContract.PokemonEntry.COLUMN_CARD_NUMBER);

        String pokeName = cursor.getString(nameColumnIndex);
        String pokeNumber = cursor.getString(numberColumnIndex);
        String cardNumber = cursor.getString(cardColumnIndex);

        nameView.setText(pokeName);

        if (pokeNumber.equals("n")) {
            numberView.setText(" ");
        } else {
            numberView.setText(pokeNumber);
        }

        cardView.setText(cardNumber);
    }
}
