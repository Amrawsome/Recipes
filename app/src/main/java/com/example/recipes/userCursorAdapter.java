package com.example.recipes;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class userCursorAdapter extends CursorAdapter {
    public userCursorAdapter(Context context, int rowlayout2, Cursor c, String[] queryColumns2, int[] columns2) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.rowlayout2,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView UID = (TextView) view.findViewById(R.id.pRID);
        TextView UFname = (TextView) view.findViewById(R.id.pFname);
        TextView Uname = (TextView) view.findViewById(R.id.pUname);
        TextView pass = (TextView) view.findViewById(R.id.pass);

        int userID = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String userFullNameName =cursor.getString(cursor.getColumnIndexOrThrow("userFullName"));
        String userName = cursor.getString(cursor.getColumnIndexOrThrow("userName"));
        String password = cursor.getString(cursor.getColumnIndexOrThrow("userPassword"));


        UID.setText(String.valueOf(userID));
        UFname.setText(userFullNameName);
        Uname.setText(userName);
        pass.setText(password);



    }
}
