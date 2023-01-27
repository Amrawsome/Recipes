package com.example.recipes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Profile extends Activity {
    int [] columns2 = new int[] {R.id.pRID,R.id.pFname,R.id.pUname};
    String [] queryColumns2 = new String []{"_id","userFullName","userName"};
    int [] columns = new int[] {R.id.recipeID,R.id.recipeName,R.id.recipeIngredients,R.id.recipeLocation,R.id.recipeInstructions};
    String [] queryColumns = new String []{"_id","recipeName","recipeIngredients","recipeLocation","recipeInstructions"};
    private DbManager dbManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        DbManager dbManager = new DbManager(this);
        dbManager.open();
        ImageButton backButton3 = (ImageButton) findViewById(R.id.back3);
        backButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, startMenu.class);
                startActivity(intent);
            }
        });
        SharedPreferences sp = getApplicationContext().getSharedPreferences("userprefs", Context.MODE_PRIVATE);
        String Uname = sp.getString("username","");
        String password = sp.getString("password","");
        User s = dbManager.getUserSP(Uname, password);
        int id2 = s._userID;

            Cursor cursor1 = dbManager.getUser(id2);
             ListView listView2 = (ListView) findViewById(R.id.list2);
             userCursorAdapter adapter2 = new userCursorAdapter(this, R.layout.rowlayout2, cursor1, queryColumns2, columns2);
            listView2.setAdapter(adapter2);
            Cursor cursor = dbManager.getUserRecipes(id2);
            ListView listView = (ListView) findViewById(R.id.list);
            RecipesCursorAdapter adapter = new RecipesCursorAdapter(this, R.layout.rowlayout, cursor, queryColumns, columns);
            listView.setAdapter(adapter);
        }
}



