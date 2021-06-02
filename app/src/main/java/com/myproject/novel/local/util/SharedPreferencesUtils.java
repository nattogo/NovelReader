package com.myproject.novel.local.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {


    public static void setParam(Context context, String key, Object object) {

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(UC.STORE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        switch (type) {
            case "String":
                editor.putString(key, (String) object);
                break;
            case "Integer":
                editor.putInt(key, (Integer) object);
                break;
            case "Boolean":
                editor.putBoolean(key, (Boolean) object);
                break;
            case "Float":
                editor.putFloat(key, (Float) object);
                break;
            case "Long":
                editor.putLong(key, (Long) object);
                break;
        }

        editor.apply();
    }

    public static Object getParam(Context context, String key, Object defaultObject) {
        if (context == null) {
            return null;
        }
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(UC.STORE, Context.MODE_PRIVATE);

        switch (type) {
            case "String":
                return sp.getString(key, (String) defaultObject);
            case "Integer":
                return sp.getInt(key, (Integer) defaultObject);
            case "Boolean":
                return sp.getBoolean(key, (Boolean) defaultObject);
            case "Float":
                return sp.getFloat(key, (Float) defaultObject);
            case "Long":
                return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

}
