//@author Jake Cox (so you no who to blame)


package com.nicodangelo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private static final String[] ALL_KEYS = new String[] {KEY_ID, KEY_CREATED_AT, KEY_NAME, KEY_AMOUNT, KEY_LOW_AMOUNT};

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
    public long createItemRow(Item item, String table)
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
    public long updateItemRow(Item item, String table)
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

    //Deletes an Item using the Item ID and table
    public boolean deleteItemRow(int id, String table)
    {
        String where = KEY_ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        if(table.equalsIgnoreCase("items_main"))
            return db.delete(TABLE_ITEMS_MAIN, where, null) != 0;
        else if(table.equalsIgnoreCase("items_grocery"))
            return db.delete(TABLE_ITEMS_GROCERY, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom1"))
            return db.delete(TABLE_ITEMS_CUSTOM1, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom2"))
            return db.delete(TABLE_ITEMS_CUSTOM2, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom3"))
            return db.delete(TABLE_ITEMS_CUSTOM3, where, null) != 0;
        else
        {
            System.out.println("METHOD deleteItem(returns:BOOLEAN,takes:ID) : Item not deleted");
            return false;
        }
    }

    //Deletes an Item using the Item NAME and table (not recommended)
    public boolean deleteItemRow(String name, String table)
    {
        String where = KEY_NAME + " = " + name;
        SQLiteDatabase db = this.getWritableDatabase();
        if(table.equalsIgnoreCase("items_main"))
            return db.delete(TABLE_ITEMS_MAIN, where, null) != 0;
        else if(table.equalsIgnoreCase("items_grocery"))
            return db.delete(TABLE_ITEMS_GROCERY, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom1"))
            return db.delete(TABLE_ITEMS_CUSTOM1, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom2"))
            return db.delete(TABLE_ITEMS_CUSTOM2, where, null) != 0;
        else if(table.equalsIgnoreCase("items_custom3"))
            return db.delete(TABLE_ITEMS_CUSTOM3, where, null) != 0;
        else
        {
            System.out.println("METHOD deleteItem(returns:BOOLEAN,takes:NAME) : Item not deleted (this method is not reliable ATM)");
            return false;
        }
    }

    //get a particular Item from the given table with the given ID
    //I = Item returned
    public Item getItemRowI(int id, String table)
    {
        Item i = new Item();
        Cursor c = null;
        SQLiteDatabase db = getReadableDatabase();

        if(table.equalsIgnoreCase("items_main"))
        {
            String where = KEY_ID + " = " + id;
            c = db.query(true, TABLE_ITEMS_MAIN, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_grocery"))
        {
            String where = KEY_ID + " = " + id;
            c = db.query(true, TABLE_ITEMS_GROCERY, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom1"))
        {
            String where = KEY_ID + " = " + id;
            c = db.query(true, TABLE_ITEMS_CUSTOM1, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom2"))
        {
            String where = KEY_ID + " = " + id;
            c = db.query(true, TABLE_ITEMS_CUSTOM2, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom3"))
        {
            String where = KEY_ID + " = " + id;
            c = db.query(true, TABLE_ITEMS_CUSTOM3, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }

        if(c == null)
        {
            System.out.println("METHOD getItemRow(returns:Item,takes:ID) : failed to Assign the cursor a value");
        }

        i.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        i.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        i.setAmount(c.getInt(c.getColumnIndex(KEY_AMOUNT)));
        i.setLow_amount(c.getColumnIndex(KEY_LOW_AMOUNT));
        i.setCreated_at(String.valueOf(c.getColumnIndex(KEY_CREATED_AT)));

        return i;
    }

    //get a particular Item from the given table with the given Name
    //I = item returned
    public Item getItemRowI(String name, String table)
    {
        Item i = new Item();
        Cursor c = null;
        SQLiteDatabase db = getReadableDatabase();

        if(table.equalsIgnoreCase("items_main"))
        {
            String where = KEY_NAME + " = " + name;
            c = db.query(true, TABLE_ITEMS_MAIN, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_grocery"))
        {
            String where = KEY_NAME + " = " + name;
            c = db.query(true, TABLE_ITEMS_GROCERY, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom1"))
        {
            String where = KEY_NAME + " = " + name;
            c = db.query(true, TABLE_ITEMS_CUSTOM1, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom2"))
        {
            String where = KEY_NAME + " = " + name;
            c = db.query(true, TABLE_ITEMS_CUSTOM2, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom3"))
        {
            String where = KEY_NAME + " = " + name;
            c = db.query(true, TABLE_ITEMS_CUSTOM3, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }

        if(c == null)
        {
            System.out.println("METHOD getItemRow(returns:ITEM,takes:NAME) : failed to Assign the cursor a value");
        }

        i.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        i.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        i.setAmount(c.getInt(c.getColumnIndex(KEY_AMOUNT)));
        i.setLow_amount(c.getColumnIndex(KEY_LOW_AMOUNT));
        i.setCreated_at(String.valueOf(c.getColumnIndex(KEY_CREATED_AT)));

        return i;
    }

    //get a particular cursor with an item from the given table with the given ID
    //C = Cursor returned
    public Cursor getItemRowC(int id, String table)
    {
        Cursor c = null;
        SQLiteDatabase db = getReadableDatabase();

        if(table.equalsIgnoreCase("items_main"))
        {
            String where = KEY_ID + " = " + id;
            c = db.query(true, TABLE_ITEMS_MAIN, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_grocery"))
        {
            String where = KEY_ID + " = " + id;
            c = db.query(true, TABLE_ITEMS_GROCERY, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom1"))
        {
            String where = KEY_ID + " = " + id;
            c = db.query(true, TABLE_ITEMS_CUSTOM1, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom2"))
        {
            String where = KEY_ID + " = " + id;
            c = db.query(true, TABLE_ITEMS_CUSTOM2, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom3"))
        {
            String where = KEY_ID + " = " + id;
            c = db.query(true, TABLE_ITEMS_CUSTOM3, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }

        if(c == null)
        {
            System.out.println("METHOD getItemRow(returns:CURSOR,takes:ID) : failed to Assign the cursor a value");
        }

        return c;
    }

    //get a particular cursor with an item from the given table with the given name
    //C = Cursor returned
    public Cursor getItemRowC(String name, String table)
    {
        Cursor c = null;
        SQLiteDatabase db = getReadableDatabase();

        if(table.equalsIgnoreCase("items_main"))
        {
            String where = KEY_NAME + " = " + name;
            c = db.query(true, TABLE_ITEMS_MAIN, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_grocery"))
        {
            String where = KEY_NAME + " = " + name;
            c = db.query(true, TABLE_ITEMS_GROCERY, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom1"))
        {
            String where = KEY_NAME + " = " + name;
            c = db.query(true, TABLE_ITEMS_CUSTOM1, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom2"))
        {
            String where = KEY_NAME + " = " + name;
            c = db.query(true, TABLE_ITEMS_CUSTOM2, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }
        else if(table.equalsIgnoreCase("items_custom3"))
        {
            String where = KEY_NAME + " = " + name;
            c = db.query(true, TABLE_ITEMS_CUSTOM3, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
        }

        if(c == null)
        {
            System.out.println("METHOD getItemRow(returns:CURSOR,takes:NAME) : failed to Assign the cursor a value");
        }

        return c;
    }

    //getting all the items from the given table
    public List<Item> getAllItems(String table)
    {
        List<Item> items = new ArrayList<Item>();
        String selectQuery = "";
        if(table.equalsIgnoreCase("items_main"))
            selectQuery = "SELECT * FROM " + TABLE_ITEMS_MAIN;
        else if(table.equalsIgnoreCase("items_grocery"))
            selectQuery = "SELECT * FROM " + TABLE_ITEMS_GROCERY;
        else if(table.equalsIgnoreCase("items_custom1"))
            selectQuery = "SELECT * FROM " + TABLE_ITEMS_CUSTOM1;
        else if(table.equalsIgnoreCase("items_custom2"))
            selectQuery = "SELECT * FROM " + TABLE_ITEMS_CUSTOM2;
        else if(table.equalsIgnoreCase("items_custom3"))
            selectQuery = "SELECT * FROM " + TABLE_ITEMS_CUSTOM3;

        Log.e(LOG,selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        //loop through the items and add them to the arrayList
        if(c.moveToFirst())
        {
            do{
                Item i = new Item();
                i.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                i.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                i.setAmount(c.getInt(c.getColumnIndex(KEY_AMOUNT)));
                i.setLow_amount(c.getColumnIndex(KEY_LOW_AMOUNT));
                i.setCreated_at(String.valueOf(c.getColumnIndex(KEY_CREATED_AT)));

                //add the item to last
                items.add(i);
            }while(c.moveToNext());
        }
        return items;
    }

    //delete all the Items of the given table(be careful they will go away forever!!!)
    public void deleteALL(String table)
    {
        Cursor c = getAllRows(table);
        long rowId = c.getColumnIndexOrThrow(KEY_ID);
        if(c.moveToFirst())
        {
            do{
                deleteItemRow(c.getInt((int)rowId), table);
            }while(c.moveToNext());
        }
        c.close();
    }

    //gets all the information in a table and stores it in a cursor(only good way to do this I think...)
    public Cursor getAllRows(String table)
    {
        String chooseTable = "";
        Cursor c = null;

        if(table.equalsIgnoreCase("table_main"))
            chooseTable = TABLE_ITEMS_MAIN;
        else if(table.equalsIgnoreCase("table_grocery"))
            chooseTable = TABLE_ITEMS_GROCERY;
        else if(table.equalsIgnoreCase("table_custom1"))
            chooseTable = TABLE_ITEMS_CUSTOM1;
        else if(table.equalsIgnoreCase("table_custom2"))
            chooseTable = TABLE_ITEMS_CUSTOM2;
        else if(table.equalsIgnoreCase("table_custom3"))
            chooseTable = TABLE_ITEMS_CUSTOM3;
        else
            chooseTable = "NOT_A_TABLE";

        SQLiteDatabase db = this.getWritableDatabase();
        String where = null;

        if(!chooseTable.equalsIgnoreCase("NOT_A_TABLE"))
        {
            c = db.query(true, chooseTable, ALL_KEYS, where, null, null, null, null, null);
            if(c != null)
            {
                c.moveToFirst();
            }
            return c;
        }
        else
        {
            System.out.println("METHOD getAllRows(returns:CURSOR,takes:table) : failed to assign table to SQL command");
            return c;
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
