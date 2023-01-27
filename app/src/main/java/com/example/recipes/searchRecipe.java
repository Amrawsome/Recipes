package com.example.recipes;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class searchRecipe extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        int [] columns = new int[] {R.id.recipeName,R.id.recipeIngredients,R.id.recipeLocation,R.id.recipeInstructions};
        String [] queryColumns = new String []{"recipeName","recipeIngredients","recipeLocation","recipeInstructions"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        DbManager dbManager = new DbManager(this);
        dbManager.open();
        ImageButton backButton6 = (ImageButton) findViewById(R.id.back6);
        backButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(searchRecipe.this, startMenu.class);
                startActivity(intent);
            }
        });

        Button searching = (Button)findViewById(R.id.searching);
        searching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView search = findViewById(R.id.search);
                String searched = search.getText().toString();
                Cursor cursor = dbManager.getRecipSearch(searched);
                if(cursor.getCount() == 0){
                    Toast.makeText(getApplicationContext(), "Recipe Does Not Exist", Toast.LENGTH_SHORT).show();
                }
                ListView listView = (ListView) findViewById(R.id.list);
                searchRecipesCursorAdapter adapter = new searchRecipesCursorAdapter(view.getContext(), R.layout.rowlayoutsearch, cursor, queryColumns, columns);
                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Cursor select = (Cursor) adapterView.getItemAtPosition(i);
                        String string  =select.getString(1);
                        String string1 = select.getString(2);
                        String string2 = select.getString(3);
                        String string3 = select.getString(4);
                        Intent intent = new Intent(searchRecipe.this,clickedActivity.class);
                        intent.putExtra("rName",string);
                        intent.putExtra("rINGRED",string1);
                        intent.putExtra("rLOC",string2);
                        intent.putExtra("rINST",string3);
                        startActivity(intent);
                    }
                });

            }
        });






    }
}

