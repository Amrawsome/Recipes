package com.example.recipes;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class RecipesCursorAdapter extends CursorAdapter {
    public RecipesCursorAdapter(Context context, int rowlayout, Cursor c, String[] queryColumns, int[] columns) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.rowlayout,viewGroup,false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView RID = (TextView) view.findViewById(R.id.recipeID);
        TextView Rname = (TextView) view.findViewById(R.id.recipeName);
        TextView RING = (TextView) view.findViewById(R.id.recipeIngredients);
        TextView RLOC = (TextView) view.findViewById(R.id.recipeLocation);
        TextView RINST = (TextView) view.findViewById(R.id.recipeInstructions);

        int recipeID = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String recipeName =cursor.getString(cursor.getColumnIndexOrThrow("recipeName"));
        String recipeIngredients = cursor.getString(cursor.getColumnIndexOrThrow("recipeIngredients"));
        String recipeInstructions = cursor.getString(cursor.getColumnIndexOrThrow("recipeInstructions"));
        String recipeLocation = cursor.getString(cursor.getColumnIndexOrThrow("recipeLocation"));

        RID.setText(String.valueOf(recipeID));
        Rname.setText(recipeName);
        RING.setText(recipeIngredients);
        RLOC.setText(recipeLocation);
        RINST.setText(recipeInstructions);



    }
}



