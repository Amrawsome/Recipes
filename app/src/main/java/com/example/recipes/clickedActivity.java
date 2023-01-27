package com.example.recipes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class clickedActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clickedscreen);
        ImageButton backButton1 = (ImageButton)findViewById(R.id.back7);
        backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(clickedActivity.this,searchRecipe.class);
                startActivity(intent);
            }
        });
        //retrieve data sent with intent
        // retrieve the extra data sent with the intent
        Intent intent =getIntent();
        String string = intent.getStringExtra("rName");
        String string1 = intent.getStringExtra("rINGRED");
        String string2 = intent.getStringExtra("rLOC");
        String string3 = intent.getStringExtra("rINST");
        TextView text = findViewById(R.id.r1);
        text.setText(string);
        TextView text2 = findViewById(R.id.r2);
        text2.setText(string1);
        TextView text3 = findViewById(R.id.r3);
        text3.setText(string2);
        TextView text4 = findViewById(R.id.r4);
        text4.setText(string3);

    }
}
