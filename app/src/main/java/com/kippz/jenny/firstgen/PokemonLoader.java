package com.kippz.jenny.firstgen;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Jenny on 07/10/2016.
 */

public class PokemonLoader extends AsyncTaskLoader<List<Pokemon>>{

    private String mUrl;

    public PokemonLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Pokemon> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        //Perform network request, parse response, extract a list of pokemon
        List<Pokemon> pokemon = QueryUtils.fetchPokemonData(mUrl);
        return pokemon;
    }
}
