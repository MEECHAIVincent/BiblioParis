package com.example.biblioparislocal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.biblioparislocal.models.ApiRecords;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Preference {
    private static final String PREFERENCE_CITY = "city";
    private static final String PREFERENCE_FAVORIS = "favoris";

    private static SharedPreferences getPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);

    }

    public static void setCity(Context context, String city){
        getPreference(context).edit().putString(PREFERENCE_CITY, city).apply();
    }

    // getter
    public  static  String getCity (Context context) {
        return getPreference(context).getString(PREFERENCE_CITY, null);
    }

    public  static  List<ApiRecords>  getFavoris (Context context) {
        List<ApiRecords> apiRecordsList = new ArrayList<>();

        String json = getPreference(context).getString(PREFERENCE_FAVORIS, null);

        if(json != null) {
            apiRecordsList = new Gson().fromJson(json, new TypeToken<ArrayList<ApiRecords>>() {}.getType());
        }

        return apiRecordsList;
    }


    public static void setFavoris(Context context, ApiRecords item) {

        List<ApiRecords> apiRecordsList = getFavoris(context);
        apiRecordsList.add(item); // TODO :gestion des elemens dupliqués / suppression

        // json
        String json = new Gson().toJson(apiRecordsList);

        getPreference(context).edit().putString(PREFERENCE_FAVORIS, json).apply();
    }
}
