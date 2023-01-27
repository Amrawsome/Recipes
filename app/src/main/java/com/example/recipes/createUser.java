package com.example.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class createUser extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createuser);
        DbManager dbManager = new DbManager(this);
        dbManager.open();
        ImageButton backButton1 = (ImageButton)findViewById(R.id.back1);
        backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(createUser.this,Login.class);
                startActivity(intent);
            }
        });
        Button connectButton = (Button) findViewById(R.id.cuSubmit);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textFN = findViewById(R.id.userFname);
                String fullname = textFN.getText().toString();
                TextView textUN = findViewById(R.id.userUName);
                String username = textUN.getText().toString();
                TextView textP = findViewById(R.id.userPassword);
                String password = textP.getText().toString();
                if(fullname.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Make Sure All Boxes Are Filled", Toast.LENGTH_SHORT).show();
                }
                else if(dbManager.valCount(username) >= 1){
                    Toast.makeText(getApplicationContext(), "User Name Already Exists Please Try Again", Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("Insert: ", "Inserting ..");
                    dbManager.addUser(new User(1, fullname, username, password));
                    Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(createUser.this, Login.class);
                    startActivity(intent);

                }

            }
        });

    }

}