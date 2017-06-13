package com.kippz.jenny.firstgen;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Log;

import com.kippz.jenny.firstgen.data.PokemonContract.PokemonEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenny on 07/10/2016.
 */

public final class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils(){

    }

    public static List<Pokemon> fetchPokemonData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        List<Pokemon> pokemon = extractPokemonFromJson(jsonResponse);
        return pokemon;
    }

    //Returns a new URL object from the given string url
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    //Make an http request to the given url and return a string in response
    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";

        //If URL is null, return early
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000); //milliseconds
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //If successful, response code 200
            //then read input stream and parse teh response
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the results", e);
        } finally {
            //Close urlConnection and inputStream after use
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //Convert the input stream to a string which contains the whole json response
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Pokemon> extractPokemonFromJson(String pokemonJson) {
        //If json is empty or null, return early
        if (TextUtils.isEmpty(pokemonJson)) {
            return null;
        }

        List<Pokemon>  pokemon = new ArrayList<>();

        ContentValues values = new ContentValues();

        try {
            JSONObject baseJsonResponse = new JSONObject(pokemonJson);

            JSONArray cardArray = baseJsonResponse.optJSONArray("cards");

            //Iterate the jsonArray and print the info if JsonObjects
            for (int i = 0; i < cardArray.length(); i++) {
                JSONObject currentPokemon = cardArray.getJSONObject(i);
                String name = currentPokemon.getString("name");
                String number = "";
                String hp = "";
                String attackName;
                String attackText;
                String attackDamage;
                if (cardArray.getJSONObject(i).has("nationalPokedexNumber") ) {
                    number = currentPokemon.getString("nationalPokedexNumber");
                } else { number = "n"; }
//                JSONObject types = currentPokemon.getJSONObject("types");
                String subtype = currentPokemon.getString("subtype");
                String supertype = currentPokemon.getString("supertype");
                if (cardArray.getJSONObject(i).has("hp")) {
                    hp = currentPokemon.getString("hp");
                } else { hp = "n"; }
                if (cardArray.getJSONObject(i).has("attacks")) {
                    JSONArray Attack = currentPokemon.getJSONArray("attacks");
                    JSONObject currentAttack = Attack.getJSONObject(0);
                    attackName = currentAttack.getString("name");
                    attackText = currentAttack.getString("text");
                    attackDamage = currentAttack.getString("damage");
                } else {
                    attackName = "n";
                    attackText = "n";
                    attackDamage = "n";
                }
                values.put(PokemonEntry.COLUMN_POKE_NAME, name);
                values.put(PokemonEntry.COLUMN_POKE_NUMBER, number);
                values.put(PokemonEntry.COLUMN_POKE_HP, hp);
                values.put(PokemonEntry.COLUMN_ATTACK_NAME,attackName);
                values.put(PokemonEntry.COLUMN_ATTACK_TEXT, attackText);
                values.put(PokemonEntry.COLUMN_ATTACK_DAMAGE, attackDamage);


//                pokemon.add(new Pokemon(number, name, subtype, supertype, hp, attackName, attackText, attackDamage));
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the pokemon JSON results", e);
        } finally {
//            curser.close();
        }
        return pokemon;
    }
}
