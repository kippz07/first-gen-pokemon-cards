package com.kippz.jenny.firstgen;

/**
 * Created by Jenny on 07/10/2016.
 */

public class Pokemon {

    private String mNumber;
    private String mName;
    private String mSubtype;
    private String mSupertype;
    private String mHp;
    private String mAttackName;
    private String mAttackText;
    private String mAttackDamage;
    private String mAttackName2;
    private String mAttackText2;
    private String mAttackDamage2;
    private String mType1;
    private String mType2;
    private String mSetCode;
    private String mCardNumber;
    private String mRarity;
    private String mWeakType;
    private String mWeakValue;
    private String mAbilityName;
    private String mAbilityText;
    private String mResistType;
    private String mResistValue;


    public Pokemon(String number, String name, String subtype, String supertype, String hp,
                   String attackName, String attackText, String attackDamage, String attackName2, String attackText2, String attackDamage2, String type1, String type2,
                   String setCode, String cardNumber, String rarity, String weakType, String weakValue,
                   String abilityName, String abilityText, String resistType, String resistValue) {
        mNumber = number;
        mName = name;
        mSubtype = subtype;
        mSupertype = supertype;
        mHp = hp;
        mAttackName = attackName;
        mAttackText = attackText;
        mAttackDamage = attackDamage;
        mAttackName2 = attackName2;
        mAttackText2 = attackText2;
        mAttackDamage2 = attackDamage2;
        mType1 = type1;
        mType2 = type2;
        mSetCode = setCode;
        mCardNumber = cardNumber;
        mRarity = rarity;
        mWeakType = weakType;
        mWeakValue = weakValue;
        mAbilityName = abilityName;
        mAbilityText = abilityText;
        mResistType = resistType;
        mResistValue = resistValue;
    }

    public String getNumber() {
        return mNumber;
    }

    public String getName() {
        return mName;
    }

    public String getSubtype() {
        return mSubtype;
    }

    public String getSupertype() {
        return mSupertype;
    }

    public String getHp() {
        return mHp;
    }

    public String getAttackName() {
        return mAttackName;
    }

    public String getAttackText() {
        return mAttackText;
    }

    public String getAttackDamage() {
        return mAttackDamage;
    }

    public String getType1() {
        return mType1;
    }

    public String getSetCode() {
        return mSetCode;
    }

}