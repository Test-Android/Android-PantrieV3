package com.example.jetts.listviewtestything;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainList extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        ArrayList<String> tempList = new ArrayList<String>();
        ListAdapter adapter = new ListAdapter(this,tempList);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
        tempList.add("Penises");

        TextView itemName = (TextView) findViewById(R.id.itemNameTextView);
        itemName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                  System.out.println("Clicked");
            }
        });
    }
}
