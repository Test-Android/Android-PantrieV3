//@author Jake Cox
//this is the same as the db handler but should actually work... implementing it is a bit tricky though:)
//TODO: add in the KEY_CREATED_AT to all the things, and fix up adn add settings
// ------------------------------------ DBAdapter ---------------------------------------------

package com.nicodangelo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DBHandler
{
    /////////////////////////////////////////////////////////////////////
    //	Constants & Data
    /////////////////////////////////////////////////////////////////////

    // For logging:
    private static final String TAG = "DBAdapter";

    // DB Fields
    public static final String KEY_ID = "_id";
    public static final int COL_ROWID = 0;

    //these are the fields for the database (Not settings)
    public static final String KEY_NAME = "name";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_LOW_AMOUNT = "low_amount";
    public static final String KEY_CREATED_AT = "created_at";

    //field numbers (0 = KEY_ROWID, 1=...)
    //these give you access to a particular field by referencing its name
    public static final int COL_NAME = 1;
    public static final int COL_AMOUNT = 2;
    public static final int COL_LOW_AMOUNT = 3;
    public static final int COL_CREATED_AT = 4;

    public static final String[] ALL_KEYS = new String[] {KEY_ID, KEY_NAME, KEY_AMOUNT, KEY_LOW_AMOUNT, KEY_CREATED_AT};

    // DB info: it's name, and the table we are using.
    public static final String DATABASE_NAME = "MyDb";
    public static final String TABLE_ITEMS_MAIN = "items_main";
    public static final String TABLE_SETTINGS = "settings";
    public static final String TABLE_ITEMS_GROCERY = "items_grocery";
    public static final String TABLE_ITEMS_CUSTOM1 = "items_custom1";
    public static final String TABLE_ITEMS_CUSTOM2 = "items_custom2";
    public static final String TABLE_ITEMS_CUSTOM3 = "items_custom3";

    // Track DB version if a new version of your app changes the format.
    //this will erase the database and recreate it... atm.
    public static final int DATABASE_VERSION = 14;

    private static final String DATABASE_CREATE_SQL_ITEMS_MAIN =
            "create table " + TABLE_ITEMS_MAIN
                    + " (" + KEY_ID + " integer primary key autoincrement, "
                    // + KEY_{...} + " {type} not null"
                    //	- Key is the column name you created above.
                    //	- {type} is one of: text, integer, real, blob
                    //  - "not null" means it is a required field (must be given a value).
                    // NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
                    + KEY_NAME + " text not null unique, "
                    + KEY_AMOUNT + " integer not null, "
                    + KEY_LOW_AMOUNT + " integer not null,"
                    + KEY_CREATED_AT + " datetime "
                    + ");";

    private static final String DATABASE_CREATE_SQL_ITEMS_GROCERY =
            "create table " + TABLE_ITEMS_GROCERY
                    + " (" + KEY_ID + " integer primary key autoincrement, "
                    // + KEY_{...} + " {type} not null"
                    //	- Key is the column name you created above.
                    //	- {type} is one of: text, integer, real, blob
                    //  - "not null" means it is a required field (must be given a value).
                    // NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
                    + KEY_NAME + " text not null unique, "
                    + KEY_AMOUNT + " integer not null, "
                    + KEY_LOW_AMOUNT + " integer not null,"
                    + KEY_CREATED_AT + " datetime "
                    + ");";

    private static final String DATABASE_CREATE_SQL_ITEMS_CUSTOM1 =
            "create table " + TABLE_ITEMS_CUSTOM1
                    + " (" + KEY_ID + " integer primary key autoincrement, "
                    // + KEY_{...} + " {type} not null"
                    //	- Key is the column name you created above.
                    //	- {type} is one of: text, integer, real, blob
                    //  - "not null" means it is a required field (must be given a value).
                    // NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
                    + KEY_NAME + " text not null unique, "
                    + KEY_AMOUNT + " integer not null, "
                    + KEY_LOW_AMOUNT + " integer not null,"
                    + KEY_CREATED_AT + " datetime "
                    + ");";

    private static final String DATABASE_CREATE_SQL_ITEMS_CUSTOM2 =
            "create table " + TABLE_ITEMS_CUSTOM2
                    + " (" + KEY_ID + " integer primary key autoincrement, "
                    // + KEY_{...} + " {type} not null"
                    //	- Key is the column name you created above.
                    //	- {type} is one of: text, integer, real, blob
                    //  - "not null" means it is a required field (must be given a value).
                    // NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
                    + KEY_NAME + " text not null unique, "
                    + KEY_AMOUNT + " integer not null, "
                    + KEY_LOW_AMOUNT + " integer not null,"
                    + KEY_CREATED_AT + " datetime "
                    + ");";

    private static final String DATABASE_CREATE_SQL_ITEMS_CUSTOM3 =
            "create table " + TABLE_ITEMS_CUSTOM3
                    + " (" + KEY_ID + " integer primary key autoincrement, "
                    // + KEY_{...} + " {type} not null"
                    //	- Key is the column name you created above.
                    //	- {type} is one of: text, integer, real, blob
                    //  - "not null" means it is a required field (must be given a value).
                    // NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
                    + KEY_NAME + " text not null unique, "
                    + KEY_AMOUNT + " integer not null, "
                    + KEY_LOW_AMOUNT + " integer not null,"
                    + KEY_CREATED_AT + " datetime "
                    + ");";

    // Context of application who uses us.
    private final Context context;

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    /////////////////////////////////////////////////////////////////////
    //	Public methods:
    /////////////////////////////////////////////////////////////////////

    public DBHandler(Context ctx)
    {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    // Open the database connection.
    public DBHandler open()
    {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close()
    {
        myDBHelper.close();
    }

    // Add a new set of values to the database.
    public long insertRow(Item item, String table)
    {
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, item.getName());
        initialValues.put(KEY_AMOUNT, item.getAmount());
        initialValues.put(KEY_LOW_AMOUNT, item.getLow_amount());

        // Insert it into the database.
        if(table.equalsIgnoreCase("items_main"))
            return db.insert(TABLE_ITEMS_MAIN, null, initialValues);
        else if(table.equalsIgnoreCase("items_custom1"))
            return db.insert(TABLE_ITEMS_CUSTOM1, null, initialValues);
        else if(table.equalsIgnoreCase("items_custom2"))
            return db.insert(TABLE_ITEMS_CUSTOM2, null, initialValues);
        else if(table.equalsIgnoreCase("items_custom3"))
            return db.insert(TABLE_ITEMS_CUSTOM3, null, initialValues);
        else
        {
            System.out.println("METHOD: insertRow(Item item, String table) -- table not recognized correctly");
            return -999;
        }
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId, String table)
    {
        String where = KEY_ID + "=" + rowId;

        if(table.equalsIgnoreCase("items_main"))
            return db.delete(TABLE_ITEMS_MAIN, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom1"))
            return db.delete(TABLE_ITEMS_CUSTOM1, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom2"))
            return db.delete(TABLE_ITEMS_CUSTOM2, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom3"))
            return db.delete(TABLE_ITEMS_CUSTOM3, where, null) != 0;
        else
        {
            System.out.println("METHOD: deleteRow(long rowId, String table) -- table not recognized correctly");
            return false;
        }
    }

    // Delete a row from the database, by item name (name)
    //keeping in mind that it will delete all items with that same name:)
    public boolean deleteRow(String name, String table)
    {
        String where = KEY_NAME + "=" + name;

        if(table.equalsIgnoreCase("items_main"))
            return db.delete(TABLE_ITEMS_MAIN, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom1"))
            return db.delete(TABLE_ITEMS_CUSTOM1, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom2"))
            return db.delete(TABLE_ITEMS_CUSTOM2, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom3"))
            return db.delete(TABLE_ITEMS_CUSTOM3, where, null) != 0;
        else
        {
            System.out.println("METHOD: deleteRow(String name, String table) -- table not recognized correctly");
            return false;
        }
    }

    //guess what this does:)
    public void deleteAll(String table)
    {
        Cursor c = getAllRows(table);
        long rowId = c.getColumnIndexOrThrow(KEY_ID);
        if (c.moveToFirst())
        {
            do
            {
                deleteRow(c.getLong((int) rowId), table);
            }
            while (c.moveToNext());
        }
        c.close();
    }

    // Return all data in the database.
    //this one I just copied I really dont quite understand
    //how it works:)
    public Cursor getAllRows(String table)
    {
        String where = null;
        Cursor c = null;

        if(table.equalsIgnoreCase("items_main"))
        {
            c = 	db.query(true, TABLE_ITEMS_MAIN, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom1"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM1, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom2"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM2, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom3"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM3, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else
        {
            System.out.println("METHOD: getAllRows(String table) -- table not recognized correctly");
        }

        if (c != null)
        {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Item getRowItem(long rowId, String table)
    {
        Item item = new Item();
        String where = KEY_ID + "=" + rowId;
        Cursor c = null;

        if(table.equalsIgnoreCase("items_main"))
        {
            c = 	db.query(true, TABLE_ITEMS_MAIN, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom1"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM1, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom2"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM2, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom3"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM3, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else
        {
            System.out.println("METHOD: getRowItem(long rowId, String table) -- table not recognized correctly");
        }

        if (c != null) {
            c.moveToFirst();
        }

        if(c != null)
        {
            item.setName(c.getString(c.getColumnIndex(KEY_NAME)));
            item.setAmount(c.getInt(c.getColumnIndex(KEY_AMOUNT)));
        }

        return item;
    }

    // Get a specific row (by rowId)
    public Item getRowItem(String name, String table)
    {
        Item item = new Item();
        String where = KEY_NAME + "=" + name;
        Cursor c = null;

        if(table.equalsIgnoreCase("items_main"))
        {
            c = 	db.query(true, TABLE_ITEMS_MAIN, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom1"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM1, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom2"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM2, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom3"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM3, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else
        {
            System.out.println("METHOD: getRowItem(String name, String table) -- table not recognized correctly");
        }

        if (c != null) {
            c.moveToFirst();
        }

        item.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        item.setAmount(c.getInt(c.getColumnIndex(KEY_AMOUNT)));

        return item;
    }

    // Get a specific row (by rowId)
    public Cursor getRowCursor(long rowId, String table)
    {
        String where = KEY_ID + "=" + rowId;
        Cursor c = null;

        if(table.equalsIgnoreCase("items_main"))
        {
            c = 	db.query(true, TABLE_ITEMS_MAIN, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom1"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM1, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom2"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM2, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom3"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM3, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else
        {
            System.out.println("METHOD: getRowCursor(long rowId, String table) -- table not recognized correctly");
        }

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by name)
    public Cursor getRowCursor(String name, String table)
    {
        String where = KEY_NAME + "=" + name;
        Cursor c = null;

        if(table.equalsIgnoreCase("items_main"))
        {
            c = 	db.query(true, TABLE_ITEMS_MAIN, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom1"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM1, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom2"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM2, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else if(table.equalsIgnoreCase("items_custom3"))
        {
            c = 	db.query(true, TABLE_ITEMS_CUSTOM3, ALL_KEYS,
                    where, null, null, null, null, null);
        }
        else
        {
            System.out.println("METHOD: getRowCursor(String name, String table) -- table not recognized correctly");
        }

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateRow(long rowId, String name, int amount, int lowAmount, String table)
    {
        String where = KEY_ID + "=" + rowId;
        // Create row's data:
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_NAME, name);
        newValues.put(KEY_AMOUNT, amount);
        newValues.put(KEY_LOW_AMOUNT, lowAmount);

        // Insert it into the database.
        if(table.equalsIgnoreCase("items_main"))
            return db.update(TABLE_ITEMS_MAIN, newValues, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom1"))
            return db.update(TABLE_ITEMS_CUSTOM1, newValues, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom2"))
            return db.update(TABLE_ITEMS_CUSTOM2, newValues, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom3"))
            return db.update(TABLE_ITEMS_CUSTOM3, newValues, where, null) != 0;
        else
        {
            System.out.println("METHOD: updateRow(long rowId, String name, int amount, int lowAmount, String table) -- table not recognized correctly");
            return false;
        }


    }


    /////////////////////////////////////////////////////////////////////
    //	Private Helper Classes:
    /////////////////////////////////////////////////////////////////////

    /**
     * Private class which handles database creation and upgrading.
     * Used to handle low-level database access.
     * at the moment if you upgrade the table it will delete all the information
     * in that table... this will be fixed later
     */
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db)
        {
            _db.execSQL(DATABASE_CREATE_SQL_ITEMS_MAIN);
            _db.execSQL(DATABASE_CREATE_SQL_ITEMS_GROCERY);
            _db.execSQL(DATABASE_CREATE_SQL_ITEMS_CUSTOM1);
            _db.execSQL(DATABASE_CREATE_SQL_ITEMS_CUSTOM2);
            _db.execSQL(DATABASE_CREATE_SQL_ITEMS_CUSTOM3);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_MAIN);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_GROCERY);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_CUSTOM1);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_CUSTOM2);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_CUSTOM3);


            // Recreate new database:
            onCreate(_db);
        }
    }

    //get the datetime
    public String getDateTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    //get the datetime
    public static String getDateAndTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
