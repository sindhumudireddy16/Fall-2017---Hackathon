package com.umkc.medspace;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class Singleton extends Application {
    private static Singleton _instance;
    private static String SHARED_PREFERENCES_NAME = "MedSpace";

    public Singleton() {
        super();
    }

    // For lazy initialisation
    public static synchronized Singleton getInstance() {
        if (_instance == null) {
            _instance = new Singleton();
        }
        return _instance;
    }

    public static void clear() {
        _instance = null;
    }

    //for saving in pref
    public static void setPref(String key, String value, Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static String getPref(String key, Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME, MODE_PRIVATE);
//        String value = myPrefs.getString(key, null);
        String value = myPrefs.getString(key, "");
        return value;
    }

    public static void clearPref(String key, Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        myPrefs.edit().remove(key).commit();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
    }
}
