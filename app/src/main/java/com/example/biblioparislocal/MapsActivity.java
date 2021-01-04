package com.example.biblioparislocal;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.google.android.gms.common.api.Api;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private List<ApiRecords> records;
    private Map<String, ApiFields> markers = new HashMap<String, ApiFields>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng paris = new LatLng(lat, lng);
        //mMap.addMarker(new MarkerOptions().position(paris).title("Paris"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(paris));
        //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paris, 11));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                ApiFields fields = markers.get(marker.getId());
                // envoie l'objet Field
                Toast.makeText(MapsActivity.this, "ID: "+marker.getId()+" - "+fields.getLibelle1(), Toast.LENGTH_SHORT).show();
            }
        });

        getData(mMap);

    }

    private void getData(GoogleMap mMap){

        if (!Network.isNetworkAvailable(MapsActivity.this)) {
            FastDialog.showDialog(
                    MapsActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté"
            );
            return;
        }


        //Requete HTTP
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = Constant.URL;


        //Request a string response from the provided URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        Log.e("volley", "onResponse" + json);

                        ApiBiblio api = new Gson().fromJson(json, ApiBiblio.class);

                        records = api.getRecords();

                        if (records != null && records.size() > 0) {

                            for (int i = 0; i < records.size(); i++) {
                                ApiFields fields = records.get(i).getFields();

                                //add Markers

                                Marker marker = mMap.addMarker(
                                        new MarkerOptions()
                                                .position(
                                                        new LatLng(
                                                                fields.getCoordonnees_finales()[0],
                                                                fields.getCoordonnees_finales()[1]
                                                        )
                                                )
                                                .title(fields.getLibelle1())
                                                .snippet(fields.getComment())
                                );
                                markers.put(marker.getId(), fields); // pour associer l'identifiant d'un Marker aux données (de l'objet Fields)

                                if (i == 0) {
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(
                                            new LatLng(
                                                    fields.getCoordonnees_finales()[0],
                                                    fields.getCoordonnees_finales()[1]
                                            )
                                    ));
                                }
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley", "onResponse" + error);
            }

        });

        queue.add(stringRequest);

    }

    }