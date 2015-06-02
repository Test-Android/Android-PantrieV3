package com.nicodangelo.pantrie;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nicodangelo.list.AllTables;
import com.nicodangelo.list.GroceryTable;
import com.nicodangelo.util.Settings;


public class Home extends ActionBarActivity
{
    Button pantrieMain;
    Button grocery;

    Button extraOne;
    Button extraTwo;
    Button extraThree;

    Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        pantrieMain = (Button) findViewById(R.id.pantrieMain);
        grocery = (Button) findViewById(R.id.grocery);

        extraOne = (Button) findViewById(R.id.extraOne);
        extraTwo = (Button) findViewById(R.id.extraTwo);
        extraThree = (Button) findViewById(R.id.extraThree);

        settings = (Button) findViewById(R.id.settings);

        extraOne.setVisibility(Button.INVISIBLE);
        extraTwo.setVisibility(Button.INVISIBLE);
        extraThree.setVisibility(Button.INVISIBLE);

// THIS IS THE ONCLICKLISTENER FOR THE PANTRIE MAIN LIST BUTTON
        pantrieMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, AllTables.class);
                i.putExtra("tableName", "items_main");
                startActivity(i);
            }
        });
// THIS IS THE ONCLICKLISTENER FOR THE GROCERY LIST BUTTON
        grocery.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this,  GroceryTable.class);
                startActivity(i);
            }
        });
// THIS IS THE ONCLICKLISTENER FOR THE EXTRAONE LIST BUTTON
        extraOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, AllTables.class);
                i.putExtra("tableName", "items_custom1");
                startActivity(i);
            }
        });
// THIS IS THE ONCLICKLISTENER FOR THE EXTRATWO LIST BUTTON
        extraOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, AllTables.class);
                i.putExtra("tableName", "items_custom2");
                startActivity(i);
            }
        });
// THIS IS THE ONCLICKLISTENER FOR THE EXTRATHREE LIST BUTTON
        extraOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, AllTables.class);
                i.putExtra("tableName", "items_custom3");
                startActivity(i);
            }
        });
// THIS IS THE ONCLICKLISTENER FOR THE SETTINGS ACTIVITY BUTTON
        settings.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View view)
           {
               Intent i = new Intent(Home.this, Settings.class);
               startActivity(i);
           }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
