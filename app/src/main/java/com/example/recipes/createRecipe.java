package com.example.recipes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class createRecipe extends Activity implements LocationListener {
    private LocationManager locationManager;
    private long minTime = 30;
    private float minDistance = 2;
    private static final int MY_PERMISSION_GPS = 1;
    public static String Country;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createrecipe);
        DbManager dbManager = new DbManager(this);
        dbManager.open();
        Toast.makeText(getApplicationContext(),"Submitting A Recipe Will Use Your Location To Tag The Recipe" , Toast.LENGTH_SHORT).show();
        ImageButton backButton2 = (ImageButton)findViewById(R.id.back2);
        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(createRecipe.this,startMenu.class);
                startActivity(intent);
            }
        });

        Button submitRecipe = (Button)findViewById(R.id.crSubmit);
        submitRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textRN = findViewById(R.id.Rname);
                String recipename = textRN.getText().toString();
                TextView textING = findViewById(R.id.RING);
                String recipeIngredients = textING.getText().toString();
                TextView textINST = findViewById(R.id.RINST);
                String recipeInstructions = textINST.getText().toString();
                TextView textLOC = findViewById(R.id.recipeLocation);
                String LOC = textLOC.getText().toString();
                if(LOC.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Getting Location,Please Wait 1 Second" , Toast.LENGTH_SHORT).show();
                }
                else if(recipename.isEmpty() || recipeIngredients.isEmpty() || recipeInstructions.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Make Sure All Boxes Are Filled", Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("Insert: ", "Inserting ..");
                    SharedPreferences sp = getApplicationContext().getSharedPreferences("userprefs",Context.MODE_PRIVATE);
                    String Uname = sp.getString("username","");
                    String password = sp.getString("password","");
                    User s = dbManager.getUserSP(Uname, password);
                    int id = s._userID;
                    dbManager.addRecipe(new Recipe(1,recipename,recipeIngredients,recipeInstructions,LOC,id));
                    Toast.makeText(getApplicationContext(), "Recipe Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(createRecipe.this, startMenu.class);
                    startActivity(intent);
                }


            }
        });
        setUpLocationTracking();
    }

    private void setUpLocationTracking() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(createRecipe.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(createRecipe.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_GPS);


        }
        else { // permission granted
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,this);
            // request location updating to start
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();
        // use the Location object passed in to retrieve the current GPS latitude and longitude
        Geocoder geo = new Geocoder(createRecipe.this.getApplicationContext(), Locale.getDefault());
        try {
            List<Address> address = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if(address.size() > 0)
            {
                 Country = address.get(0).getCountryName();
                 TextView LOC = findViewById(R.id.recipeLocation);
                 LOC.setText(Country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onProviderDisabled(String provider) {

    }

    public void onProviderEnabled(String provider) {

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    @Override
    // this is a callback method associated with the user having entered in their permission -
    // the compiler will prompt you to add this call back method, when you putin the code for the permission check earlier.

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_GPS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                } else {
                    Toast.makeText(getApplicationContext(),"Please turn on permissions",Toast.LENGTH_SHORT).show();
                    // show a Toast message to say you need to switch on permissions
                    // to be added:
                }
                break;
        }
    }


    // Part 3 Android lifecycle methods for controlling battery power
    @Override
    protected void onResume() {
        super.onResume();
        // switch on location updates: to be added
    }


    @Override
    protected void onPause() {
        super.onPause();

        // switch off location updates: to be added
    }


}
