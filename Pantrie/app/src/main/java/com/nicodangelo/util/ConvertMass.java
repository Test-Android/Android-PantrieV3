package com.nicodangelo.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.nicodangelo.pantrie.R;


public class ConvertMass extends ActionBarActivity
{
    //They may be not used however these are all the "Objects" in the class that are active and do things:)
    private EditText input;
    private TextView output;
    private Button itemSelect1;
    private Button itemSelect2;
    private Button convert;
    private AlertDialog.Builder a;
    private AlertDialog ad;
    private AlertDialog.Builder b;
    private AlertDialog bd;
    private double inputNum = 0;
    private double outputNum = 0;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private String index1 = "grams";
    private String index2 = "grams";

    //these are not used yet but are placements for later (keep these:))
    private String onceString;
    private String gramString;
    private String poundString;
    private String milligramString;
    private String kilogramsString;
    private String teaspoonString;
    private String tablespoonString;
    private String fluidOunceString;
    private String gillString;
    private String cupString;
    private String pintString;
    private String quartString;
    private String gallonString;
    private String milliliterString;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_mass);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mass");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //When you hit the Conversion button all this happens!!
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void makeConversion(View view)
    {
        //TODO: add the UI elements for this activity
        //input = (EditText) findViewById(R.id.input);
        if (TextUtils.isEmpty(input.getText().toString()))
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Try Again... please", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        //layout1.removeAllViews();
        //layout2.removeAllViews();
        //TODO: add the UI elements for this activity
        //output = (TextView) findViewById(R.id.output);

        inputNum = Double.parseDouble(input.getText().toString());

        //There are a lot of if statements here:) They do all the maths for the conversions
        if (!TextUtils.isEmpty(input.getText().toString()))
        {
            //MASS AND WEIGHT
            if(index1.equals(index2)) {outputNum = inputNum;}
            else if(index1.equals("ounces") && index2.equals("grams")) {outputNum = inputNum * 28.3;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("grams") && index2.equals("ounces")) {outputNum = inputNum * .0353;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("pounds") && index2.equals("grams")) {outputNum = inputNum * 453.59;}
            else if(index1.equals("grams") && index2.equals("pounds")) {outputNum = inputNum * 0.0022046;}
            else if (index1.equals("pounds") && index2.equals("ounces")) {outputNum = inputNum * 16.000;}
            else if(index1.equals("ounces") && index2.equals("pounds")) {outputNum = inputNum * 0.062500;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("milligrams") && index2.equals("grams")) {outputNum = inputNum / 1000.0;}
            else if(index1.equals("milligrams") && index2.equals("ounces")) {outputNum = inputNum * 0.000035274;}
            else if(index1.equals("milligrams") && index2.equals("pounds")) {outputNum = inputNum * 0.000002204;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("grams") && index2.equals("milligrams")) {outputNum = inputNum / 0.0010000;}
            else if(index1.equals("ounces") && index2.equals("milligrams")) {outputNum = inputNum * 28349.523;}
            else if(index1.equals("pounds") && index2.equals("milligrams")) {outputNum = inputNum * 453592;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("kilograms") && index2.equals("grams")) {outputNum = inputNum * 1000.0;}
            else if(index1.equals("kilograms") && index2.equals("ounces")) {outputNum = inputNum * 35.274;}
            else if(index1.equals("kilograms") && index2.equals("pounds")) {outputNum = inputNum * 2.20462;}
            else if(index1.equals("kilograms") && index2.equals("milligrams")) {outputNum = inputNum * 1000000;}
            else if(index1.equals("ounces") && index2.equals("kilograms")) {outputNum = inputNum * 0.0283495;}
            else if(index1.equals("pounds") && index2.equals("kilograms")) {outputNum = inputNum * 0.453592;}
            else if(index1.equals("grams") && index2.equals("kilograms")) {outputNum = inputNum * 0.001;}
            else if(index1.equals("milligrams") && index2.equals("kilograms")) {outputNum = inputNum * 0.000001;}

            output.setText(Double.toString(outputNum));
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Try Again... please", Toast.LENGTH_SHORT);
        }



    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //This is the first conversion button box:
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void firstConversion(View view)
    {
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //THE FIRST DIALOG BOX AND ITS ATTRIBUTES

        //TODO: add the UI elements for this activity
        //itemSelect1 = (Button) findViewById(R.id.itemSelect1);

        //create the Alert Dialog 1
        a = new AlertDialog.Builder(this)
                .setTitle("Measurements")
                .setMessage("Pick a measurement to convert from");
        a.setCancelable(false);

        //Create the button
        final Button ounces1 = new Button(ConvertMass.this);
        final Button grams1 = new Button(ConvertMass.this);
        final Button pounds1 = new Button(ConvertMass.this);
        final Button milligrams1 = new Button(ConvertMass.this);
        final Button kilograms1 = new Button (ConvertMass.this);

        //set the name to the buttons
        ounces1.setText("ounces");
        grams1.setText("grams");
        pounds1.setText("pounds");
        milligrams1.setText("milligrams");
        kilograms1.setText("kilograms");

        //all the on click listeners...
        ounces1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "ounces";
                itemSelect1.setText("ounces");
            }
        });

        grams1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "grams";
                itemSelect1.setText("grams");

            }
        });

        pounds1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "pounds";
                itemSelect1.setText("pounds");

            }
        });

        milligrams1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "milligrams";
                itemSelect1.setText("milligrams");
            }
        });

        kilograms1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "kilograms";
                itemSelect1.setText("kilograms");
            }
        });


        //NOW ADD THE STUFF CREATED ABOVE TO THE VIEW WITH A NEW LAYOUT

        layout1 = new LinearLayout(ConvertMass.this);
        layout1.setOrientation(LinearLayout.VERTICAL);
        //Add the buttons to the layout
        layout1.addView(ounces1);
        layout1.addView(grams1);
        layout1.addView(pounds1);
        layout1.addView(milligrams1);
        layout1.addView(kilograms1);
        //now set the view and create it
        a.setView(layout1);
        ad = a.create();
        ad = a.show();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //This is the first conversion button box:
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void secondConversion(View view)
    {
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //THE SECOND DIALOG BOX AND ITS ATTRIBUTES

        //TODO: add the UI elements for this activity
        //itemSelect2 = (Button) findViewById(R.id.itemSelect2);

        b = new AlertDialog.Builder(this)
                .setTitle("Measurements")
                .setMessage("Pick a measurement to convert from");
        b.setCancelable(false);

        //Create the button
        final Button ounces2 = new Button(ConvertMass.this);
        final Button grams2 = new Button(ConvertMass.this);
        final Button pounds2 = new Button(ConvertMass.this);
        final Button milligrams2 = new Button(ConvertMass.this);
        final Button kilograms2 = new Button(ConvertMass.this);

        //set the name to the buttons
        ounces2.setText("ounces");
        grams2.setText("grams");
        pounds2.setText("pounds");
        milligrams2.setText("milligrams");
        kilograms2.setText("kilograms");

        //all the on click listeners...
        ounces2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "ounces";
                itemSelect2.setText("ounces");
            }
        });

        grams2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "grams";
                itemSelect2.setText("grams");
            }
        });

        pounds2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "pounds";
                itemSelect2.setText("pounds");
            }
        });

        milligrams2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "milligrams";
                itemSelect2.setText("milligrams");
            }
        });

        kilograms2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "kilograms";
                itemSelect2.setText("kilograms");
            }
        });

        layout2 = new LinearLayout(ConvertMass.this);
        layout2.setOrientation(LinearLayout.VERTICAL);
        //Add the buttons to the layout
        layout2.addView(ounces2);
        layout2.addView(grams2);
        layout2.addView(pounds2);
        layout2.addView(milligrams2);
        layout2.addView(kilograms2);
        //now set the view and create it
        b.setView(layout2);
        bd = b.create();
        bd = b.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_convert_mass, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings)
        {
            Intent i = new Intent(ConvertMass.this, Settings.class);
            startActivity(i);
            return true;
        }
        //TODO: add the UI elements for this activity
        /*if (id == R.id.volume)
        {
            Intent i = new Intent(ConvertMass.this, ConvertVolume.class);
            startActivity(i);
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
