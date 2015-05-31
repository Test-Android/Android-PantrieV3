package com.nicodangelo.list;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nicodangelo.database.Item;
import com.nicodangelo.pantrie.R;

import java.util.ArrayList;

public class AllTables extends ActionBarActivity
{
    ListView mainList;
    DefaultTableAdapter adapter;
    ArrayList<Item> listMain;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tables);

//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Your Pantrie");

        System.out.println("You motherfuckers");
        listMain = new ArrayList<Item>();

        Item apple = new Item(1,"apple",16);
        listMain.add(apple);
        mainList = (ListView) findViewById(R.id.allTablesListView);
        adapter = new DefaultTableAdapter(this,R.layout.default_table_adapter,listMain);

        mainList.setAdapter(adapter);


    }
}
