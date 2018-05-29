package com.kippz.jenny.firstgen;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.kippz.jenny.firstgen.data.PokemonContract;

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

public class LoadDataActivity extends AppCompatActivity {


    public static final String LOG_TAG = LoadDataActivity.class.getSimpleName();

    private static final String POKE_REQUEST_URL =
            "https://api.pokemontcg.io/v1/cards?setCode=base1|base2|base3|base5|gym1|gym2&pageSize=1000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        Cursor mCursor = getContentResolver().query(
                PokemonContract.PokemonEntry.CONTENT_URI,
                null,
                null,
                null,
                null);

        if (!mCursor.moveToFirst()) {
            new PokemonAsyncTask().execute();
        } else {
            Intent intent = new Intent(LoadDataActivity.this, MainActivity.class);
            startActivity(intent);
        }
        mCursor.close();
    }

    private class PokemonAsyncTask extends AsyncTask<URL, Void, List<Pokemon>> {

        @Override
        protected List<Pokemon> doInBackground(URL... urls) {

            URL url = createUrl(POKE_REQUEST_URL);

            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error", e);
                return null;
            }

            List<Pokemon> pokemon = extractPokemonFromJson(jsonResponse);

//
            return pokemon;
        }

        @Override
        protected void onPostExecute(List<Pokemon> pokemon) {

            Intent intent = new Intent(LoadDataActivity.this, MainActivity.class);
            startActivity(intent);

        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        private String makeHttpRequest(URL url) throws IOException {
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

        private String readFromStream(InputStream inputStream) throws IOException {
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

        private List<Pokemon> extractPokemonFromJson(String pokemonJson) {
            //If json is empty or null, return early
            if (TextUtils.isEmpty(pokemonJson)) {
                return null;
            }

            List<Pokemon> pokemon = new ArrayList<>();
//
            ContentValues values = new ContentValues();

            try {
                JSONObject baseJsonResponse = new JSONObject(pokemonJson);

                JSONArray cardArray = baseJsonResponse.optJSONArray("cards");

                //Iterate the jsonArray and print the info if JsonObjects
                for (int i = 0; i < cardArray.length(); i++) {
                    JSONObject currentPokemon = cardArray.getJSONObject(i);
                    String name = currentPokemon.getString("name");
                    String cardNumber = currentPokemon.getString("number");
                    String rarity = currentPokemon.getString("rarity");
                    String rawNumber = "";
                    String number = "";
                    String hp = "";
                    String attackName;
                    String attackText;
                    String attackDamage;
                    String attackName2;
                    String attackText2;
                    String attackDamage2;
                    String weakType;
                    String weakValue;
                    String abilityName;
                    String abilityText;
                    String resistType = "None";
                    String resistValue = "n";
                    if (cardArray.getJSONObject(i).has("nationalPokedexNumber")) {
                        rawNumber = currentPokemon.getString("nationalPokedexNumber");

                        switch (rawNumber.length()) {
                            case 1:
                                number = "#00" + rawNumber;
                                break;
                            case 2:
                                number = "#0" + rawNumber;
                                break;
                            case 3:
                                number = "#" + rawNumber;
                                break;
                        }
                    } else {
                        number = "n";
                    }

                    ArrayList<String> types = new ArrayList<>();
                    String type1;
                    String type2;
                    if (cardArray.getJSONObject(i).has("types")) {
                        JSONArray typeArray = currentPokemon.optJSONArray("types");
                        for (int j = 0; j < typeArray.length(); j++) {
                            String currentType = typeArray.getString(j);
                            types.add(currentType.toString());
                        }
                        type1 = types.get(0);
                        if (types.size() == 2) {
                            type2 = types.get(1);
                        } else type2 = "n";
                    } else {
                        type1 = "n";
                        type2 = "n";
                    }

                    String subtype = currentPokemon.getString("subtype");
                    String supertype = currentPokemon.getString("supertype");
                    String setCode = currentPokemon.getString("setCode");
                    if (cardArray.getJSONObject(i).has("hp")) {
                        hp = currentPokemon.getString("hp");
                    } else {
                        hp = "n";
                    }
                    if (cardArray.getJSONObject(i).has("attacks")) {
                        JSONArray Attack = currentPokemon.getJSONArray("attacks");
                        JSONObject currentAttack = Attack.getJSONObject(0);
                        attackName = currentAttack.getString("name");
                        attackText = currentAttack.getString("text");
                        attackDamage = currentAttack.getString("damage");

                        if (Attack.length() == 2) {
                            JSONObject currentAttack2 = Attack.getJSONObject(1);
                            attackName2 = currentAttack2.getString("name");
                            attackText2 = currentAttack2.getString("text");
                            attackDamage2 = currentAttack2.getString("damage");
                        } else {
                            attackName2 = "n";
                            attackText2 = "n";
                            attackDamage2 = "n";
                        }

                    } else {
                        attackName = "n";
                        attackText = "n";
                        attackDamage = "n";
                        attackName2 = "n";
                        attackText2 = "n";
                        attackDamage2 = "n";
                    }

                    if (cardArray.getJSONObject(i).has("weaknesses")) {
                        JSONArray weaknesses = currentPokemon.getJSONArray("weaknesses");
                        JSONObject currentWeakness = weaknesses.getJSONObject(0);
                        weakType = currentWeakness.getString("type");
                        weakValue = currentWeakness.getString("value");
                    } else {
                        weakType = "None";
                        weakValue = "n";
                    }

                    if (cardArray.getJSONObject(i).has("resistances")) {
                        JSONArray resistances = currentPokemon.getJSONArray("resistances");
                        JSONObject currentResistance = resistances.getJSONObject(0);
                        weakType = currentResistance.getString("type");
                        weakValue = currentResistance.getString("value");
                    } else {
                        resistType = "None";
                        resistValue = "n";
                    }

                    if (cardArray.getJSONObject(i).has("ability")) {
                        JSONObject currentAbility = currentPokemon.getJSONObject("ability");
                        abilityName = currentAbility.getString("name");
                        abilityText = currentAbility.getString("text");
                    } else {
                        abilityName = "n";
                        abilityText = "n";
                    }

                    values.put(PokemonContract.PokemonEntry.COLUMN_POKE_NAME, name);
                    values.put(PokemonContract.PokemonEntry.COLUMN_POKE_NUMBER, number);
                    values.put(PokemonContract.PokemonEntry.COLUMN_POKE_HP, hp);
                    values.put(PokemonContract.PokemonEntry.COLUMN_ATTACK_NAME, attackName);
                    values.put(PokemonContract.PokemonEntry.COLUMN_ATTACK_TEXT, attackText);
                    values.put(PokemonContract.PokemonEntry.COLUMN_ATTACK_DAMAGE, attackDamage);
                    values.put(PokemonContract.PokemonEntry.COLUMN_ATTACK_NAME2, attackName2);
                    values.put(PokemonContract.PokemonEntry.COLUMN_ATTACK_TEXT2, attackText2);
                    values.put(PokemonContract.PokemonEntry.COLUMN_ATTACK_DAMAGE2, attackDamage2);
                    values.put(PokemonContract.PokemonEntry.COLUMN_TYPE_1, type1);
                    values.put(PokemonContract.PokemonEntry.COLUMN_TYPE_2, type2);
                    values.put(PokemonContract.PokemonEntry.COLUMN_SUPERTYPE, supertype);
                    values.put(PokemonContract.PokemonEntry.COLUMN_SET_CODE, setCode);
                    values.put(PokemonContract.PokemonEntry.COLUMN_SUBTYPE, subtype);
                    values.put(PokemonContract.PokemonEntry.COLUMN_RARITY, rarity);
                    values.put(PokemonContract.PokemonEntry.COLUMN_WEAK_TYPE, weakType);
                    values.put(PokemonContract.PokemonEntry.COLUMN_WEAK_VALUE, weakValue);
                    values.put(PokemonContract.PokemonEntry.COLUMN_CARD_NUMBER, cardNumber);
                    values.put(PokemonContract.PokemonEntry.COLUMN_ABILITY_NAME, abilityName);
                    values.put(PokemonContract.PokemonEntry.COLUMN_ABILITY_TEXT, abilityText);
                    values.put(PokemonContract.PokemonEntry.COLUMN_RESIST_TYPE, resistType);
                    values.put(PokemonContract.PokemonEntry.COLUMN_RESIST_VALUE, resistValue);

                    Uri newUri = getContentResolver().insert(PokemonContract.PokemonEntry.CONTENT_URI, values);

                    pokemon.add(new Pokemon(number, name, subtype, supertype, hp, attackName,
                            attackText, attackDamage, attackName2,
                            attackText2, attackDamage2,type1, type2, setCode, cardNumber, rarity,
                            weakType, weakValue, abilityName, abilityText, resistType, resistValue));
                }
            } catch (JSONException e) {
                Log.e("QueryUtils", "Problem parsing the pokemon JSON results", e);
            }

            return pokemon;
        }

    }
}

