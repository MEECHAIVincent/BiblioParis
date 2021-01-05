package com.example.biblioparislocal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppActivity extends AppCompatActivity {

    protected void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_default,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //Bouton menu + retour

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); //fermeture activity
                break;
            case R.id.menu_home:
                Intent homeIntent = new Intent(AppActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.menu_favoris:
                Intent favorisIntent = new Intent(AppActivity.this, FavorisActivity.class);
                startActivity(favorisIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null) {
            if (!(this instanceof HomeActivity)) {
                //bouton retour ->
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }


}
