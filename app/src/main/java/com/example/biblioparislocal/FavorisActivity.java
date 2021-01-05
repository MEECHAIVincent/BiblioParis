package com.example.biblioparislocal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.biblioparislocal.models.ApiRecords;
import com.example.biblioparislocal.utils.Preference;

import java.util.List;

public class FavorisActivity extends AppActivity {

    private ListView listViewData;
    private List<ApiRecords> records;
    private Button resetButton;
    private ArrayAdapter<Object> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);
        listViewData = findViewById(R.id.listViewData);
        resetButton = findViewById(R.id.reset_button);

        records = Preference.getFavoris(FavorisActivity.this);

        listViewData.setAdapter(
                new BiblioAdapter(
                        FavorisActivity.this,
                        R.layout.item_biblio,
                        records

                )

        );


        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Objet Resataurant
                ApiRecords item = records.get(position);

                // Intent
                Intent intentDetails = new Intent(FavorisActivity.this, DetailActivity.class);


                //passage de données simple
                intentDetails.putExtra("objet", item);

                //passage de l'objet restaurant
                //intentDetails.putExtra("object", item);

                startActivity(intentDetails);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preference.resetFavoris(FavorisActivity.this);
                Toast.makeText(FavorisActivity.this, "List reseted", Toast.LENGTH_SHORT).show();
            }
        });

    }


}