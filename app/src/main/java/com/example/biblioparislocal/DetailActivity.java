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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biblioparislocal.models.ApiBiblio;
import com.example.biblioparislocal.models.ApiFields;
import com.example.biblioparislocal.models.ApiRecords;
import com.example.biblioparislocal.utils.Constant;
import com.example.biblioparislocal.utils.FastDialog;
import com.example.biblioparislocal.utils.Network;
import com.example.biblioparislocal.utils.Preference;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class DetailActivity extends AppActivity implements OnMapReadyCallback {

    //déclaration
    private TextView textViewComment;
    private TextView textViewVoie;
    private TextView textViewVille;
    private TextView textViewCp;
    private TextView textViewLibelle1;
    private GoogleMap mMap;
    private ApiRecords item;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //TODO : code spécifique
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textViewLibelle1 = findViewById(R.id.textViewLabelle1);
        textViewComment = findViewById(R.id.textViewComment);
        textViewVoie = findViewById(R.id.textViewVoie);
        textViewVille = findViewById(R.id.textViewVille);
        textViewCp = findViewById(R.id.textViewCp);

        // Récupère le donnée pour le transfert de data
        if (getIntent().getExtras() != null) {
            item = (ApiRecords) getIntent().getExtras().get("objet");


            textViewLibelle1.setText(item.getFields().getLibelle1());
            textViewComment.setText(item.getFields().getComment());
            textViewVoie.setText(item.getFields().getVoie());
            textViewVille.setText(item.getFields().getAdresse_ville());
            textViewCp.setText(item.getFields().getCp());
        }
    }

    public void submit(View view) {
        Preference.setFavoris(DetailActivity.this, item);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String Coord = item.getFields().getCoordonnees_ban();
        String[] parts = Coord.split(",");
        String Lat  = parts[0];
        String Lng = parts[1];


        // Add a marker PositionJson and move the camera
        LatLng paris = new LatLng(Double.parseDouble(Lat), Double.parseDouble(Lng));
        mMap.addMarker(new MarkerOptions().position(paris).title(item.getFields().getLibelle1()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(paris));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paris, 11));




    }



}