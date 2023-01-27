package com.example.recipes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class startMenu extends AppCompatActivity implements LocationListener {
    private LocationManager locationManager;
    private long minTime = 30;
    private float minDistance = 2;
    private static final int MY_PERMISSION_GPS = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startscreen);
        DbManager dbManager = new DbManager(this);
        dbManager.open();
        Button profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startMenu.this, Profile.class);
                startActivity(intent);
            }
        });
        Button createrecipe = (Button) findViewById(R.id.createRecipe);
        createrecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startMenu.this, createRecipe.class);
                startActivity(intent);
            }
        });
        Button deleterecipe = (Button) findViewById(R.id.delete);
        deleterecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startMenu.this, deleteRecipe.class);
                startActivity(intent);
            }
        });
        Button updaterecipe = (Button) findViewById(R.id.update);
        updaterecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startMenu.this, updateRecipe.class);
                startActivity(intent);
            }
        });
        Button searchrecipe = (Button) findViewById(R.id.search);
        searchrecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startMenu.this, searchRecipe.class);
                startActivity(intent);
            }
        });

        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startMenu.this, Login.class);
                startActivity(intent);
            }
        });


    setUpLocationTracking();

    }
    private void setUpLocationTracking() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(startMenu.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(startMenu.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_GPS);


        }
        else { // permission granted
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance, (LocationListener) this);
            // request location updating to start
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}