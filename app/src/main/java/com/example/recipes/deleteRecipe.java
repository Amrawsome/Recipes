package com.example.recipes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class deleteRecipe extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
        DbManager dbManager = new DbManager(this);
        dbManager.open();
        Toast.makeText(getApplicationContext(), "Deleting Will Permanently Remove The Recipe ", Toast.LENGTH_SHORT).show();
        ImageButton backButton4 = (ImageButton) findViewById(R.id.back4);
        backButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(deleteRecipe.this, startMenu.class);
                startActivity(intent);
            }
        });
        Button delete = (Button) findViewById(R.id.deleteButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getApplicationContext().getSharedPreferences("userprefs", Context.MODE_PRIVATE);
                String Uname = sp.getString("username", "");
                String password = sp.getString("password", "");
                User d = dbManager.getUserSP(Uname, password);
                int id = d._userID;
                Recipe recipesSP = dbManager.getRecipesSP(id);
                String regex = "\\d+";

                //Toast.makeText(getApplicationContext(),recipeID , Toast.LENGTH_SHORT).show();


                TextView deleteID = findViewById(R.id.deleteID);
                String id2 = deleteID.getText().toString();
                if (id2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Make Sure All Boxes Have Something In Them", Toast.LENGTH_SHORT).show();
                }
                if (id2.matches(regex)) {


                    if (recipesSP._recipeID != Integer.valueOf(id)) {
                        Toast.makeText(getApplicationContext(), "Please Delete One Of YOUR Recipes", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Delete: ", "Deleting ..");
                        dbManager.deleteRecipe(id2);
                        Toast.makeText(getApplicationContext(), "Recipe Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(deleteRecipe.this, startMenu.class);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Please Only Enter Numbers In The ID Box", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}