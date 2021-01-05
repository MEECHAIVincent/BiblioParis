package com.example.biblioparislocal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void showCarte(View view) {
        // Intent permet de passer d'une page à une autre
        Intent intentCarte = new Intent(HomeActivity.this, MapsActivity.class);
        // passage d'un paramètre
        startActivity(intentCarte);
    }

    public void showListe(View view) {
        // Intent permet de passer d'une page à une autre
        Intent intentListe = new Intent(HomeActivity.this, ListingActivity.class);
        // passage d'un paramètre
        startActivity(intentListe);
    }



}