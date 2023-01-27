package com.example.recipes;


import static com.example.recipes.myDatabaseHelper.KEY_FNAME;
import static com.example.recipes.myDatabaseHelper.KEY_INGREDIENTS;
import static com.example.recipes.myDatabaseHelper.KEY_INSTRUCTIONS;
import static com.example.recipes.myDatabaseHelper.KEY_LOCATION;
import static com.example.recipes.myDatabaseHelper.KEY_PASSWORD;
import static com.example.recipes.myDatabaseHelper.KEY_RID;
import static com.example.recipes.myDatabaseHelper.KEY_RNAME;
import static com.example.recipes.myDatabaseHelper.KEY_UID;
import static com.example.recipes.myDatabaseHelper.KEY_UID_FK;
import static com.example.recipes.myDatabaseHelper.KEY_UNAME;
import static com.example.recipes.myDatabaseHelper.TABLE_RECIPES;
import static com.example.recipes.myDatabaseHelper.TABLE_USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class DbManager extends AppCompatActivity {
    Context context;
    private myDatabaseHelper URdbHelper;
    private SQLiteDatabase database;

    public DbManager(Context context)
    {
        this.context = context;

    }

    public DbManager() {

    }

    public DbManager open() throws SQLException {
        URdbHelper = new myDatabaseHelper(context);
        database = URdbHelper.getWritableDatabase();
        return this;
    }

    public void close() {URdbHelper.close(); }

    void addUser(User user) {

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, user.get_userFullName()); // Task Name
        values.put(KEY_UNAME, user.get_userName()); // Task Name
        values.put(KEY_PASSWORD, user.get_userPassword());

        // Inserting Row
        database.insert(TABLE_USER, null, values);
        //2nd argument is String containing nullColumnHack

    }

    User getUserSP(String Uname, String password){
        //String selectQuery = "SELECT * FROM " + TABLE_RECIPES + "WHERE "+ KEY_UID_FK +"=?",new int[] {FK};
        
        
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE "+KEY_UNAME+"=?"+" AND "+ KEY_PASSWORD + "=?" ,new String[] {Uname,password});
        if(cursor !=null)
            cursor.moveToLast();
        User user = new User(
                cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return user;
    }

    public Cursor getUser(int id){
        //String selectQuery = "SELECT * FROM " + TABLE_RECIPES + "WHERE "+ KEY_UID_FK +"=?",new int[] {FK};
        Cursor recipesList = database.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE "+KEY_UID+"=?" ,new String[] {String.valueOf(id)});
        return recipesList;
    }

    public int countUsers(String username,String password) {
        String countUser = "SELECT userName FROM " + TABLE_USER + " WHERE userName =?";
        Cursor cursor = database.rawQuery("SELECT "+KEY_UNAME+" FROM " + TABLE_USER + " WHERE "+KEY_UNAME+" =?"+"AND "+KEY_PASSWORD+" =?",new String[] {username,password});


        return cursor.getCount();

    }

    public int valCount(String username) {
        String countUser = "SELECT userName FROM " + TABLE_USER + " WHERE userName =?";
        Cursor cursor = database.rawQuery("SELECT "+KEY_UNAME+" FROM " + TABLE_USER + " WHERE "+KEY_UNAME+" =?",new String[] {username});


        return cursor.getCount();

    }

    Recipe getRecipesSP(int spID){
        //String selectQuery = "SELECT * FROM " + TABLE_RECIPES + "WHERE "+ KEY_UID_FK +"=?",new int[] {FK};


        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_RECIPES + " WHERE "+KEY_UID_FK+"=?" ,new String[] {String.valueOf(spID)});
        if(cursor !=null)
            cursor.moveToNext();
        Recipe recipe = new Recipe(
                cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getInt(5));
        return recipe;
    }

    void addRecipe(Recipe recipe){

        ContentValues values = new ContentValues();
        values.put(KEY_RNAME, recipe.get_recipeName()); // Task Name
        values.put(KEY_INGREDIENTS, recipe.get_recipeIngredients()); // Task Name
        values.put(KEY_INSTRUCTIONS, recipe.get_recipeInstructions());
        values.put(KEY_LOCATION,recipe.get_recipeLocation());
        values.put(KEY_UID_FK,recipe.get_recipeUIDFK());

        // Inserting Row
        database.insert(TABLE_RECIPES, null, values);
        //2nd argument is String containing nullColumnHack
    }


    public Cursor getUserRecipes(int FK){
        //String selectQuery = "SELECT * FROM " + TABLE_RECIPES + "WHERE "+ KEY_UID_FK +"=?",new int[] {FK};
        Cursor recipesList = database.rawQuery("SELECT * FROM " + TABLE_RECIPES + " WHERE "+KEY_UID_FK+"=?" ,new String[] {String.valueOf(FK)});
        return recipesList;
    }

    public Cursor getRecipSearch(String Search){
        String param1 = "%"+Search+ "%";
        //String selectQuery = "SELECT * FROM " + TABLE_RECIPES + "WHERE "+ KEY_UID_FK +"=?",new int[] {FK};
        Cursor recipesList = database.rawQuery("SELECT * FROM " + TABLE_RECIPES + " WHERE "+KEY_RNAME+" LIKE" + "?" ,new String[] {param1});
        return recipesList;
    }


    public void updateRecipe(int id,String name, String ingred, String instr, String loc,int FK){
         database.execSQL("UPDATE " + TABLE_RECIPES + " SET "+ KEY_RID + "=?"+ ","
                + KEY_RNAME +"=?"+","+KEY_INGREDIENTS+"=?"+
                ","+KEY_INSTRUCTIONS+"=?"+","+KEY_LOCATION+"=?"
                +","+KEY_UID_FK+"=?"+ "WHERE "+KEY_RID+"=?" ,new String[] {String.valueOf(id),name,ingred,instr,loc,String.valueOf(FK), String.valueOf(id)});
    }

    public void deleteRecipe(String id){
        database.execSQL("DELETE  FROM " + TABLE_RECIPES + " WHERE "+KEY_RID+"=?" ,new String[] {id});

    }

}
