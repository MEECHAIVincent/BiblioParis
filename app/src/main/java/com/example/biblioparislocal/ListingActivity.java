package com.example.biblioparislocal;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioparislocal.models.ApiBiblio;
import com.example.biblioparislocal.models.ApiRecords;
import com.example.biblioparislocal.utils.Constant;
import com.google.gson.Gson;

public class ListingActivity extends AppActivity {
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

    }
    private void parseJSON(String response) {
        ApiBiblio api = new Gson().fromJson(response, ApiBiblio.class);

        listViewData.setAdapter(
                new BiblioAdapter(
                        ListingActivity.this,
                        R.layout.item_biblio,
                        api.getRecords()
                )
        );
        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Objet Resataurant
                ApiRecords item = api.getRecords().get(position);

                // Intent
                Intent intentDetails = new Intent(ListingActivity.this, DetailActivity.class);


                //passage de données simple
                intentDetails.putExtra("Libelle1",item.getFields().getLibelle1());
                intentDetails.putExtra("Comment",item.getFields().getComment());
                intentDetails.putExtra("Cp",item.getFields().getCp());
                intentDetails.putExtra("Voie",item.getFields().getVoie());
                intentDetails.putExtra("Adresse",item.getFields().getAdresse_ville());
                intentDetails.putExtra("Coord",item.getFields().getCoordonnees_ban());

                //passage de l'objet restaurant
                //intentDetails.putExtra("object", item);

                startActivity(intentDetails);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_default,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_favoris:
                Intent detailsIntent = new Intent(ListingActivity.this, FavorisActivity.class);
                startActivity(detailsIntent);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}