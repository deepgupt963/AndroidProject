package com.test.kirana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Grocery_DATA {
    static final String DATABASE_NAME = "groceryData.db";
    static final int DATABASE_VERSION = 2;

    static final String ADD_ITEMS = "CREATE TABLE Products" + "( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "ITEM_NAME TEXT," + "PRICE INTEGER," +"ITEM_QUANTITY); ";
    public static SQLiteDatabase db;
    private final Context context;
    private static DBHelper dbHelper;

    public Grocery_DATA(Context _context) {
        context = _context;
        dbHelper = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static int count() {
        db = dbHelper.getReadableDatabase(); Cursor cur = db.rawQuery("SELECT * FROM Products", null);
        cur.moveToFirst(); return cur.getCount();
    }

    public Grocery_DATA open(){
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public static Cursor fetchAll() {
        db = dbHelper.getReadableDatabase(); return db.rawQuery("SELECT * FROM Products", null);
    }

    public static void close() {
        db.close();
    }

    public static void insertEntry(String ProductName, String ProductPrice,String ProductQuantity) {
        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("ITEM_NAME", ProductName);
        newValues.put("PRICE ", ProductPrice);
        newValues.put("ITEM_QUANTITY ", ProductQuantity);
        db.insert("Products", null, newValues);

    }

}
