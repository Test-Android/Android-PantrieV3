//@author Jake Cox (so you no who to blame)


package com.nicodangelo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private static final String TABLE_ITEMS_CUSTOM1 = "items_custom1";
    private static final String TABLE_ITEMS_CUSTOM2 = "items_custom2";
    private static final String TABLE_ITEMS_CUSTOM3 = "items_custom3";

    //ALL tables - Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    //ITEMS table - common column names
    private static final String KEY_NAME = "item_name";
    private static final String KEY_AMOUNT = "item_amount";
    private static final String KEY_LOW_AMOUNT = "item_low_amount";

    //SETTINGS table - column names
    private static final String KEY_TUTORIAL = "tutorial"; //this will be a boolean column
    private static final String KEY_THEME = "theme";
    private static final String KEY_COLOR = "color";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //TABLE CREATION STATEMENTS
///////////////////////////////////////////////////////////////////////////////////////////////////

    //TABLE_SETTINGS table create statement
    private static final String CREATE_TABLE_SETTINGS = "CREATE TABLE " + TABLE_SETTINGS
            + "( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_TUTORIAL + " TEXT, "
            + KEY_THEME + " TEXT, "
            + KEY_COLOR + " TEXT, "
            + KEY_CREATED_AT + " DATETIME"
            + ");";

    //TABLE_ITEMS_MAIN table create statement
    private static final String CREATE_TABLE_ITEMS_MAIN = "CREATE TABLE " + TABLE_ITEMS_MAIN
            + "( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT NOT NULL UNIQUE, "
            + KEY_AMOUNT + " INTEGER NOT NULL, "
            + KEY_LOW_AMOUNT + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME "
            + ");";

    //TABLE_ITEMS_GROCERY table create statement
    private static final String CREATE_TABLE_ITEMS_GROCERY = "CREATE TABLE " + TABLE_ITEMS_GROCERY
            + "( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT NOT NULL UNIQUE, "
            + KEY_AMOUNT + " INTEGER NOT NULL, "
            + KEY_LOW_AMOUNT + " INTEGER, "
            + KEY_CREATED_AT + " DATETIME"
            + ");";

    //TABLE_ITEMS_CUSTOM1 table create statement
    private static final String CREATE_TABLE_ITEMS_CUSTOM1 = "CREATE TABLE " + TABLE_ITEMS_CUSTOM1
            + "( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT NOT NULL UNIQUE, "
            + KEY_AMOUNT + " INTEGER NOT NULL, "
            + KEY_LOW_AMOUNT + " INTEGER, "
            + KEY_CREATED_AT + " DATETIME "
            + ");";

    //TABLE_ITEMS_CUSTOM2 table create statement
    private static final String CREATE_TABLE_ITEMS_CUSTOM2 = "CREATE TABLE " + TABLE_ITEMS_CUSTOM2
            + "( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT NOT NULL UNIQUE, "
            + KEY_AMOUNT + " INTEGER NOT NULL, "
            + KEY_LOW_AMOUNT + " INTEGER, "
            + KEY_CREATED_AT + " DATETIME "
            + ");";

    //TABLE_ITEMS_CUSTOM2 table create statement
    private static final String CREATE_TABLE_ITEMS_CUSTOM3 = "CREATE TABLE " + TABLE_ITEMS_CUSTOM3
            + "( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT NOT NULL UNIQUE, "
            + KEY_AMOUNT + " INTEGER NOT NULL, "
            + KEY_LOW_AMOUNT + " INTEGER, "
            + KEY_CREATED_AT + " DATETIME "
            + ");";

///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //creating required tables
        db.execSQL(CREATE_TABLE_SETTINGS);
        db.execSQL(CREATE_TABLE_ITEMS_MAIN);
        db.execSQL(CREATE_TABLE_ITEMS_GROCERY);
        db.execSQL(CREATE_TABLE_ITEMS_CUSTOM1);
        db.execSQL(CREATE_TABLE_ITEMS_CUSTOM2);
        db.execSQL(CREATE_TABLE_ITEMS_CUSTOM3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //TODO : might not need this if statement "does not call it if version has not changed?"
        //onUpgrade drop older tables
        if(newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_MAIN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_CUSTOM1);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_CUSTOM2);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_CUSTOM3);

            //create new tables
            onCreate(db);
        }
    }

    //Following will insert a row into the given table
    public long createItem(Item item, String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_AMOUNT, item.getAmount());
        values.put(KEY_LOW_AMOUNT, item.getAmount());
        values.put(KEY_CREATED_AT, getDateTime());

        //inset the row
        if (table.equalsIgnoreCase("items_main"))
        {
            long item_id = db.insert(TABLE_ITEMS_MAIN, null, values);
            return item_id;
        } else if (table.equalsIgnoreCase("items_grocery"))
        {
            long item_id = db.insert(TABLE_ITEMS_GROCERY, null, values);
            return item_id;
        } else if (table.equalsIgnoreCase("items_custom1"))
        {
            long item_id = db.insert(TABLE_ITEMS_CUSTOM1, null, values);
            return item_id;
        } else if (table.equalsIgnoreCase("items_custom2"))
        {
            long item_id = db.insert(TABLE_ITEMS_CUSTOM2, null, values);
            return item_id;
        } else if (table.equalsIgnoreCase("items_custom3"))
        {
            long item_id = db.insert(TABLE_ITEMS_CUSTOM3, null, values);
            return item_id;
        } else
        {
            System.out.println("METHOD CreateItem : failed to pick correct table");
            long item_id = -999;
            System.out.println("METHOD CreateItem Item id mismatch : Item not created, id now equals : " + item_id);
            return item_id;
        }
    }

    //Following will update a row in the given table
    public long updateItem(Item item, String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_AMOUNT, item.getAmount());
        values.put(KEY_LOW_AMOUNT, item.getAmount());
        values.put(KEY_CREATED_AT, getDateTime());

        //inset the row
        if (table.equalsIgnoreCase("items_main"))
        {
            long item_id = db.update(TABLE_ITEMS_MAIN, values, KEY_ID + " = ?", new String[] {String.valueOf(item.getId())});
            return item_id;
        } else if (table.equalsIgnoreCase("items_grocery"))
        {
            long item_id = db.update(TABLE_ITEMS_GROCERY, values, KEY_ID + " = ?", new String[] {String.valueOf(item.getId())});
            return item_id;
        } else if (table.equalsIgnoreCase("items_custom1"))
        {
            long item_id = db.update(TABLE_ITEMS_CUSTOM1, values, KEY_ID + " = ?", new String[] {String.valueOf(item.getId())});
            return item_id;
        } else if (table.equalsIgnoreCase("items_custom2"))
        {
            long item_id = db.update(TABLE_ITEMS_CUSTOM2, values, KEY_ID + " = ?", new String[] {String.valueOf(item.getId())});
            return item_id;
        } else if (table.equalsIgnoreCase("items_custom3"))
        {
            long item_id = db.update(TABLE_ITEMS_CUSTOM3, values, KEY_ID + " = ?", new String[] {String.valueOf(item.getId())});
            return item_id;
        } else
        {
            System.out.println("METHOD UpdateItem : failed to pick correct table");
            long item_id = -999;
            System.out.println("METHOD UpdateItem Item id mismatch : Item not updated, id now equals : " + item_id);
            return item_id;
        }
    }
















    //get the datetime
    private String getDateTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
