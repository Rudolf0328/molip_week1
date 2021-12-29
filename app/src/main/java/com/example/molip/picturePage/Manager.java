package com.example.molip.picturePage;

import com.example.molip.R;

import java.lang.reflect.Field;

public class Manager {
    public static String LOGGER = "LOGGER!";
    public final static int RC_DETAIL_TO_UPDATE = 1000;
    public final static int RC_CA_TO_DETAIL = 1001;
    public final static int RC_CA_TO_UPDATE = 1002;

    public static int getDrawableResId(String resName) {
        try {
            Field idField = R.drawable.class.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}