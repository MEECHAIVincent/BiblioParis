package com.example.biblioparislocal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioparislocal.models.ApiBiblio;
import com.example.biblioparislocal.models.ApiFields;
import com.example.biblioparislocal.utils.Constant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ListingActivity extends AppCompatActivity {
    private ListView listViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        listViewData = findViewById(R.id.listViewData);

        //Requete HTTP
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = Constant.URL;


        //Request a string response from the provided URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("volley", "onResponse" + response);

                        parseJSON(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley", "onResponse" + error);
            }

        });

        queue.add(stringRequest);






        //Gestion de la liste
        List<ApiFields> biblioList = new ArrayList<>();


    }
    private void parseJSON(String response) {
        ApiBiblio api = new Gson().fromJson(response, ApiBiblio.class);
    }

}