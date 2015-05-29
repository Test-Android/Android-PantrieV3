package com.nicodangelo.util;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nicodangelo.pantrie.Home;
import com.nicodangelo.pantrie.R;


public class Settings extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void launchConversionCalc(View view) {
        Intent i = new Intent(this, ConvertMass.class);
        startActivity(i);
    }

    //TODO:add jetts game(cleaner version)
    /*public void startGame(View view) {
        Intent i = new Intent(this, ArrowGame.class);
        startActivity(i);
    }*/

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(), "There is no going back now:)", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void goBack(View view)
    {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }

    //TODO:fix Database Handler
    /*public void deleteDB(View view)
    {
        DBHandler db = new DBHandler(this);
        db.open();
        db.deleteAll();
        db.close();
        Intent i = new Intent(Settings.this, AllTables.class);
        startActivity(i);
    }*/
}

