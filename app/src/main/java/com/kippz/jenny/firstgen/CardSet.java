package com.kippz.jenny.firstgen;

/**
 * Created by Jenny on 07/10/2016.
 */

//Making a class for the card set name so the name can be put into the ListView

public class CardSet {

    private String mSetName;

    private String mCode;

    private String mSort;

    public CardSet(String setName, String code, String sort) {
        mSetName = setName;
        mCode = code;
        mSort = sort;
    }

    public String getSetName() {
        return mSetName;
    }

    public String getCode() {
        return mCode;
    }

    public String getSort() {
        return mSort;
    }
}
