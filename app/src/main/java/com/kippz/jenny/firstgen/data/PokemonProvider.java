package com.kippz.jenny.firstgen.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Jenny on 15/10/2016.
 */

public class PokemonProvider extends ContentProvider {
    //uri matcher codes
    private static final int POKEMON = 100;

    private static final int POKEMON_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        sUriMatcher.addURI(PokemonContract.CONTENT_AUTHORITY, PokemonContract.PATH_POKEMON, POKEMON);
        sUriMatcher.addURI(PokemonContract.CONTENT_AUTHORITY, PokemonContract.PATH_POKEMON + "/#", POKEMON_ID);
    }

    public static final String LOG_TAG = PokemonProvider.class.getSimpleName();

    private PokemonDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new PokemonDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;
        //See if UYri matcher can match the uri
        int match = sUriMatcher.match(uri);
        switch (match) {
            case POKEMON:
                cursor = database.query(PokemonContract.PokemonEntry.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                break;
            case POKEMON_ID:
                selection = PokemonContract.PokemonEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(PokemonContract.PokemonEntry.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI");
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case POKEMON:
                return PokemonContract.PokemonEntry.CONTENT_LIST_TYPE;
            case POKEMON_ID:
                return PokemonContract.PokemonEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case POKEMON:
                return insertPokemon(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertPokemon(Uri uri, ContentValues values) {
        String name = values.getAsString(PokemonContract.PokemonEntry.COLUMN_POKE_NAME);
        String number = values.getAsString(PokemonContract.PokemonEntry.COLUMN_POKE_NUMBER);
        String hp = values.getAsString(PokemonContract.PokemonEntry.COLUMN_POKE_HP);
        String atkname = values.getAsString(PokemonContract.PokemonEntry.COLUMN_ATTACK_NAME);
        String atktext = values.getAsString(PokemonContract.PokemonEntry.COLUMN_ATTACK_TEXT);
        String atkdmg = values.getAsString(PokemonContract.PokemonEntry.COLUMN_ATTACK_DAMAGE);
        String type1 = values.getAsString(PokemonContract.PokemonEntry.COLUMN_TYPE_1);

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = database.insert(PokemonContract.PokemonEntry.TABLE_NAME, null, values);

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
