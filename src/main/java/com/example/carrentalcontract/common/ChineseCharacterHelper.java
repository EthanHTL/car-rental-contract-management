package com.example.carrentalcontract.common;


import com.github.stuxuhai.jpinyin.PinyinHelper;

public class ChineseCharacterHelper {
    public ChineseCharacterHelper() {
    }

    public static String getSpells(String characters) {
        try {
            return PinyinHelper.getShortPinyin(characters);
        } catch (Exception var2) {
            return "";
        }
    }
}
