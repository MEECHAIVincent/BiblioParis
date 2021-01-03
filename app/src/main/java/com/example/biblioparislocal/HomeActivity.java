package com.example.biblioparislocal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_default,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_carte:
                Intent carteIntent = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(carteIntent);
                break;
            case R.id.menu_details:
                Intent detailsIntent = new Intent(HomeActivity.this, DetailActivity.class);
                startActivity(detailsIntent);
        }


        return super.onOptionsItemSelected(item);
    }
}