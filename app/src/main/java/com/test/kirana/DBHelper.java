package com.test.kirana;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate (SQLiteDatabase _db){
        _db.execSQL(Grocery_DATA.ADD_ITEMS);

    }

    @Override
    public void onUpgrade (SQLiteDatabase _db, int _oldVersion, int _newVersion){
        _db.execSQL("DROP TABLE IF EXISTS Products");
        onCreate(_db);
    }
}


