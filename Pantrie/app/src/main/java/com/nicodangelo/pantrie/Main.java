package com.nicodangelo.pantrie;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class Main extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                Intent i = new Intent(Main.this, Home.class);
                startActivity(i);
            }
        };

        Thread theThread = new Thread(r);
        theThread.start();
    }

    @Override
    public void onBackPressed(){}

}
