package com.example.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        DbManager dbManager = new DbManager(this);
        dbManager.open();
        sp = getSharedPreferences("userprefs", Context.MODE_PRIVATE);
        Button cuButton = (Button) findViewById(R.id.createUser);
        Button connectButton = (Button) findViewById(R.id.loginSubmit);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textUname = findViewById(R.id.username);
                String loginUname = textUname.getText().toString();
                TextView textPassword = findViewById(R.id.password);
                String loginPassword = textPassword.getText().toString();
                SharedPreferences.Editor editor = sp.edit();

                editor.putString("username",loginUname);
                editor.putString("password",loginPassword);
                editor.commit();

                    if (dbManager.countUsers(loginUname,loginPassword) >= 1) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, startMenu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "User Does not Exist", Toast.LENGTH_SHORT).show();
                    }

            }
        });
        cuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,createUser.class);
                startActivity(intent);
            }
        });
    }



}