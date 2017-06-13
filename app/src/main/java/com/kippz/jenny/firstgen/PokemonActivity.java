package com.kippz.jenny.firstgen;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kippz.jenny.firstgen.data.PokemonContract;

public class PokemonActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{



    private static final int POKEMON_LOADER_ID = 1;

    public static final String LOG_TAG = PokemonActivity.class.getName();

    private PokemonCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        ListView pokemonListView = (ListView) findViewById(R.id.list);

        mCursorAdapter = new PokemonCursorAdapter(this, null);

        pokemonListView.setAdapter(mCursorAdapter);

        getLoaderManager().initLoader(POKEMON_LOADER_ID, null, this);

        pokemonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PokemonActivity.this, AttributesActivity.class);

                Uri currentPokemonUri = ContentUris.withAppendedId(PokemonContract.PokemonEntry.CONTENT_URI, id);

                intent.setData(currentPokemonUri);

                startActivity(intent);
            }
        });

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String sort = intent.getExtras().getString("sort");
        String [] projection = {
                PokemonContract.PokemonEntry._ID,
                PokemonContract.PokemonEntry.COLUMN_POKE_NAME,
                PokemonContract.PokemonEntry.COLUMN_POKE_NUMBER,
                PokemonContract.PokemonEntry.COLUMN_TYPE_1,
                PokemonContract.PokemonEntry.COLUMN_SUPERTYPE,
                PokemonContract.PokemonEntry.COLUMN_SET_CODE,
                PokemonContract.PokemonEntry.COLUMN_CARD_NUMBER
        };
        String selection;
        String[] selectionArgs = new String[] {name};
        if (sort.equals("types")) {
            selection = PokemonContract.PokemonEntry.COLUMN_TYPE_1 + "=?";
        } else if (sort.equals("supertype")) {
            selection = PokemonContract.PokemonEntry.COLUMN_SUPERTYPE + "=?";
        } else {
            selection = PokemonContract.PokemonEntry.COLUMN_SET_CODE + "=?";
        }

        Log.e(LOG_TAG, selection);
        Log.e(LOG_TAG, selectionArgs[0]);
//

        return new CursorLoader(this,
                PokemonContract.PokemonEntry.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                PokemonContract.PokemonEntry.COLUMN_POKE_NUMBER + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
