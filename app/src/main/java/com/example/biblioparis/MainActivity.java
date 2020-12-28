package com.example.biblioparis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer myTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO : lancement de HommeActivity
                Intent myIntent = new Intent(MainActivity.this, SideMenu.class);
                startActivity(myIntent);
                finish(); // permet de tuer la page
            }
        },2000);
    }
}