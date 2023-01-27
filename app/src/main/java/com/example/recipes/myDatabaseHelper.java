package com.example.recipes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "RecipesAppDB";
    public static final String TABLE_USER = "USERS";
    public static final String KEY_UID = "_id";
    public static final String KEY_FNAME = "userFullName";
    public static final String KEY_UNAME = "userName";
    public static final String KEY_PASSWORD = "userPassword";
    public static final String TABLE_RECIPES = "RECIPES";
    public static final String KEY_RID = "_id";
    public static final String KEY_RNAME = "recipeName";
    public static final String KEY_INGREDIENTS = "recipeIngredients";
    public static final String KEY_INSTRUCTIONS = "recipeInstructions";
    public static final String KEY_LOCATION = "recipeLocation";
    public static final String KEY_UID_FK = "recipes_userID";


    public myDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FNAME + " TEXT,"
                + KEY_UNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT"
                + ")";


        String CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES + "("
                + KEY_RID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_RNAME + " TEXT,"
                + KEY_INGREDIENTS + " TEXT,"
                + KEY_INSTRUCTIONS + " TEXT,"
                + KEY_LOCATION + " TEXT, "
                + KEY_UID_FK + " INTEGER REFERENCES " + TABLE_USER
                + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_RECIPES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        }
    }
}
