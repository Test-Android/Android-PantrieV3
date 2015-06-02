package com.nicodangelo.list;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.nicodangelo.database.DBHandler;
import com.nicodangelo.database.DatabaseHandler;
import com.nicodangelo.database.Item;
import com.nicodangelo.pantrie.R;

import java.util.ArrayList;

public class AllTables extends ActionBarActivity
{
    ListView mainList;
    DefaultTableAdapter adapter;
    ArrayList<Item> listMain;
    String tableName;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tables);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Your Pantrie");

        tableName = getIntent().getStringExtra("tableName");

        db = new DBHandler(this);
        Item apple = new Item(0,"apple",16);
        db.open();
        db.insertRow(apple,"items_main");

        listMain = new ArrayList<Item>();

        fillArrayList();

        mainList = (ListView) findViewById(R.id.allTablesListView);
        adapter = new DefaultTableAdapter(this,R.layout.default_table_adapter,listMain);

        mainList.setAdapter(adapter);

    }
    private void fillArrayList()
    {
        Item tempItem = new Item();
        tempItem = db.getRowItem(1, "items_main");
        listMain.add(tempItem);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
