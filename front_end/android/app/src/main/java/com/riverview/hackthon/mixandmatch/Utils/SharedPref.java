package com.riverview.hackthon.mixandmatch.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    public static final String FILE_KEY = "com.riverview.hackthon.mixandmatch";

    public static final String USER_REGISTER_KEY = "user_register";


    @SuppressWarnings("static-access")
    public static SharedPreferences getPref(Context ctx) {
        return ctx.getSharedPreferences(SharedPref.FILE_KEY, ctx.MODE_PRIVATE);
    }

    @SuppressWarnings("static-access")
    public static void saveString(Activity act, String key, String value) {
        SharedPreferences.Editor editor = act.getSharedPreferences(
                SharedPref.FILE_KEY, act.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    @SuppressWarnings("static-access")
    public static void saveBoolean(Activity act, String key, boolean value) {
        SharedPreferences.Editor editor = act.getSharedPreferences(
                SharedPref.FILE_KEY, act.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    @SuppressWarnings("static-access")
    public static String getString(Activity act, String key, String defaultval) {
        SharedPreferences prefs = act.getSharedPreferences(SharedPref.FILE_KEY,
                act.MODE_PRIVATE);
        return prefs.getString(key, defaultval);
    }

    @SuppressWarnings("static-access")
    public static boolean getBoolean(Context act, String key,
                                     boolean defaultval) {
        SharedPreferences prefs = act.getSharedPreferences(SharedPref.FILE_KEY,
                act.MODE_PRIVATE);
        return prefs.getBoolean(key, defaultval);
    }

    @SuppressWarnings("static-access")
    public static void saveInteger(Activity act, String key, Integer value) {
        SharedPreferences.Editor editor = act.getSharedPreferences(
                SharedPref.FILE_KEY, act.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    @SuppressWarnings("static-access")
    public static int getInteger(Activity act, String key,
                                 int defaultval) {
        SharedPreferences prefs = act.getSharedPreferences(SharedPref.FILE_KEY,
                act.MODE_PRIVATE);
        return prefs.getInt(key, defaultval);
    }

    @SuppressWarnings("static-access")
    public static void saveLong(Activity act, String key, long value) {
        SharedPreferences.Editor editor = act.getSharedPreferences(
                SharedPref.FILE_KEY, act.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    @SuppressWarnings("static-access")
    public static long getLong(Activity act, String key,
                               long defaultval) {
        SharedPreferences prefs = act.getSharedPreferences(SharedPref.FILE_KEY,
                act.MODE_PRIVATE);
        return prefs.getLong(key, defaultval);
    }

    @SuppressWarnings("static-access")
    public static void saveFloat(Activity act, String key, float value) {
        SharedPreferences.Editor editor = act.getSharedPreferences(
                SharedPref.FILE_KEY, act.MODE_PRIVATE).edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    @SuppressWarnings("static-access")
    public static float getFloat(Activity act, String key,
                                 float defaultval) {
        SharedPreferences prefs = act.getSharedPreferences(SharedPref.FILE_KEY,
                act.MODE_PRIVATE);
        return prefs.getFloat(key, defaultval);
    }

    @SuppressWarnings("static-access")
    public static void deleteString(Activity act, String key
    ) {
        SharedPreferences.Editor editor = act.getSharedPreferences(
                SharedPref.FILE_KEY, act.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.commit();
    }

    @SuppressWarnings("static-access")
    public static void deleteInt(Activity act, String key) {
        SharedPreferences.Editor editor = act.getSharedPreferences(
                SharedPref.FILE_KEY, act.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.commit();
    }


}
