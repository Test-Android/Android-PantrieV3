//@author Jake Cox (so you no who to blame)

package com.nicodangelo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    //Logcat tag : I really dont know what this is:)
    private static final String LOG = "DatabaseHandler";

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "pantrie_database";

    //Table Names
    private static final String TABLE_SETTINGS = "settings";
    private static final String TABLE_ITEMS_MAIN = "items_main";
    private static final String TABLE_ITEMS_GROCERY = "items_grocery";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2)
    {

    }
}
