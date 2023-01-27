package com.example.recipes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class updateRecipe extends Activity implements AdapterView.OnItemSelectedListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        DbManager dbManager = new DbManager(this);
        dbManager.open();
        ImageButton backButton5 = (ImageButton) findViewById(R.id.back5);
        backButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(updateRecipe.this, startMenu.class);
                startActivity(intent);
            }
        });
        Button update = (Button) findViewById(R.id.upcrSubmit);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView recID = findViewById(R.id.upRID);
                String recipeID = recID.getText().toString();
                TextView recName = findViewById(R.id.upRname);
                String recipeName = recName.getText().toString();
                TextView recING = findViewById(R.id.upRING);
                String recipeIngred = recING.getText().toString();
                TextView recLOCA = findViewById(R.id.transport);
                String recipeLOC = recLOCA.getText().toString();
                TextView recipeISTRUCT = findViewById(R.id.upRINST);
                String recipeINSTR = recipeISTRUCT.getText().toString();
                SharedPreferences sp = getApplicationContext().getSharedPreferences("userprefs", Context.MODE_PRIVATE);
                String Uname = sp.getString("username","");
                String password = sp.getString("password","");
                User d = dbManager.getUserSP(Uname, password);
                int id = d._userID;
                Recipe recipesSP = dbManager.getRecipesSP(id);
                String regex ="\\d+";

                //Toast.makeText(getApplicationContext(),recipeID , Toast.LENGTH_SHORT).show();

                if(recipeID.isEmpty()||recipeName.isEmpty()||recipeIngred.isEmpty()||recipeLOC.isEmpty()||recipeINSTR.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Make Sure All Boxes Have Something In Them", Toast.LENGTH_SHORT).show();
                }
                if(recipeID.matches(regex)) {


                if (recipesSP._recipeID != Integer.valueOf(recipeID)) {
                        Toast.makeText(getApplicationContext(), "Please Update One Of YOUR Recipes", Toast.LENGTH_SHORT).show();
                    }
                else {
                        Log.d("Update: ", "Updating ..");
                        dbManager.updateRecipe(Integer.valueOf(recipeID), recipeName, recipeIngred, recipeINSTR, recipeLOC, id);
                        Toast.makeText(getApplicationContext(), "Recipe Updates", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(updateRecipe.this, startMenu.class);
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please Only Enter Numbers In The ID Box", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Spinner spinner = findViewById(R.id.upLOC);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Countries,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        String text = parent.getItemAtPosition(i).toString();
       TextView text2 = findViewById(R.id.transport);
       text2.setText(text);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
