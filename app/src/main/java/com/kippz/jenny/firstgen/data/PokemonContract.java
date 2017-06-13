package com.kippz.jenny.firstgen.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Jenny on 10/10/2016.
 */

public final class PokemonContract {

    public static final String CONTENT_AUTHORITY = "com.kippz.jenny.firstgen";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_POKEMON = "pokemon";

    public static abstract class PokemonEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_POKEMON);

        public static final String TABLE_NAME = "pokemon";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_POKE_NAME = "name";
        public static final String COLUMN_POKE_NUMBER = "number";
        public static final String COLUMN_POKE_HP = "hp";
        public static final String COLUMN_ATTACK_NAME = "atkname";
        public static final String COLUMN_ATTACK_TEXT = "text";
        public static final String COLUMN_ATTACK_DAMAGE = "ad";
        public static final String COLUMN_ATTACK_NAME2 = "atkname2";
        public static final String COLUMN_ATTACK_TEXT2 = "text2";
        public static final String COLUMN_ATTACK_DAMAGE2 = "ad2";
        public static final String COLUMN_TYPE_1 = "type1";
        public static final String COLUMN_TYPE_2 = "type2";
        public static final String COLUMN_SUBTYPE = "subtype";
        public static final String COLUMN_SUPERTYPE = "supertype";
        public static final String COLUMN_SET_CODE = "setCode";
        public static final String COLUMN_CARD_NUMBER = "cardNumber";
        public static final String COLUMN_RARITY = "rarity";
        public static final String COLUMN_WEAK_TYPE = "weakType";
        public static final String COLUMN_WEAK_VALUE = "weakValue";
        public static final String COLUMN_ABILITY_NAME = "abilityName";
        public static final String COLUMN_ABILITY_TEXT = "abilityText";
        public static final String COLUMN_RESIST_TYPE = "resistType";
        public static final String COLUMN_RESIST_VALUE = "resistValue";

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POKEMON;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POKEMON;
    }
}
