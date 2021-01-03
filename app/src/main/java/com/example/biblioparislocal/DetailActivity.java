package com.example.biblioparislocal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioparislocal.models.ApiBiblio;
import com.example.biblioparislocal.utils.Constant;
import com.example.biblioparislocal.utils.FastDialog;
import com.example.biblioparislocal.utils.Network;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    //déclaration
    private TextView textViewComment;
    private TextView textViewVoie;
    private TextView textViewVille;
    private TextView textViewCp;
    private TextView textViewCoordonnees_Insee;
    private TextView textViewLibelle1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textViewLibelle1 = findViewById(R.id.textViewLabelle1);
        textViewComment = findViewById(R.id.textViewComment);
        textViewVoie = findViewById(R.id.textViewVoie);
        textViewVille = findViewById(R.id.textViewVille);
        textViewCp = findViewById(R.id.textViewCp);
        textViewCoordonnees_Insee = findViewById(R.id.textViewCoordonnees_Insee);



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
        textViewComment.setText(null);
        textViewCoordonnees_Insee.setText(null);
        textViewCp.setText(null);
        textViewVille.setText(null);
        textViewVoie.setText(null);
        textViewLibelle1.setText(null);
        //GSON
        ApiBiblio api = new Gson().fromJson(response, ApiBiblio.class);

        if (api.getNhits() == 67) {
            textViewLibelle1.setText(api.getRecords().get(2).getFields().getLibelle1());
            textViewComment.setText(api.getRecords().get(2).getFields().getComment());
            textViewVoie.setText(api.getRecords().get(2).getFields().getVoie());
            textViewVille.setText(api.getRecords().get(2).getFields().getAdresse_ville());
            textViewCp.setText(api.getRecords().get(2).getFields().getCp());
            textViewCoordonnees_Insee.setText(api.getRecords().get(2).getFields().getCoordonnees_insee());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_default,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                Intent detailsIntent = new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(detailsIntent);
                break;
            case R.id.menu_carte:
                Intent carteIntent = new Intent(DetailActivity.this, MapsActivity.class);
                startActivity(carteIntent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }




}