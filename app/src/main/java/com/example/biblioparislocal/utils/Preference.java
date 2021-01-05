package com.example.biblioparislocal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.biblioparislocal.models.ApiRecords;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Preference {
    private static final String PREFERENCE_FAVORIS = "favoris";

    private static SharedPreferences getPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);

    }

    public  static  List<ApiRecords>  getFavoris (Context context) {
        List<ApiRecords> apiRecordsList = new ArrayList<>();

        String json = getPreference(context).getString(PREFERENCE_FAVORIS, null);

        if(json != null) {
            apiRecordsList = new Gson().fromJson(json, new TypeToken<ArrayList<ApiRecords>>() {}.getType());
        }
        return apiRecordsList;
        }


    public static void addFavoris(Context context, ApiRecords item) {

        List<ApiRecords> apiRecordsList = getFavoris(context);
            for (int i = 0; i < apiRecordsList.size(); i++) {

                // Supprime le doublon
                if (apiRecordsList.get(i).getFields().getLibelle1().toString().equals(item.getFields().getLibelle1().toString())){
                    apiRecordsList.remove(apiRecordsList.get(i));
                }
        }
            //ajoute dans les favoris
        apiRecordsList.add(item);
        String json = new Gson().toJson(apiRecordsList);
        getPreference(context).edit().putString(PREFERENCE_FAVORIS, json).apply();

    }

    public static void resetFavoris(Context context){

        List<ApiRecords> apiRecordsList = getFavoris(context);
        for (int i = 0; i < apiRecordsList.size(); i++) {
            apiRecordsList.remove(apiRecordsList.get(i));
        }
        String json = new Gson().toJson(apiRecordsList);
        getPreference(context).edit().putString(PREFERENCE_FAVORIS, json).apply();
    }
}
