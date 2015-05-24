package com.nicodangelo.util;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.nicodangelo.pantrie.R;


public class ConvertVolume extends ActionBarActivity
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
    private String index1 = "tablespoon";
    private String index2 = "tablespoon";

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
    private String literString;
    private String deciliter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_volume);
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
        layout1.removeAllViews();
        layout2.removeAllViews();
        //TODO: add the UI elements for this activity
        //output = (TextView) findViewById(R.id.output);

        inputNum = Double.parseDouble(input.getText().toString());

        //There are a lot of if statements here:) They do all the maths for the conversions
        if (!TextUtils.isEmpty(input.getText().toString()))
        {
            //VOLUME
            if(index1.equals(index2)) {outputNum = inputNum;}
            else if(index1.equals("teaspoon") && index2.equals("tablespoon")) {outputNum = inputNum * 0.33333;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("tablespoon") && index2.equals("teaspoon")) {outputNum = inputNum * 3;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("fluid ounce") && index2.equals("teaspoon")) {outputNum = inputNum * 6;}
            else if(index1.equals("fluid ounce") && index2.equals("tablespoon")) {outputNum = inputNum * 2;}
            else if(index1.equals("teaspoon") && index2.equals("fluid ounce")) {outputNum = inputNum * 0.166667;}
            else if(index1.equals("tablespoon") && index2.equals("fluid ounce")) {outputNum = inputNum * 0.5;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("gill") && index2.equals("teaspoon")) {outputNum = inputNum * 24;}
            else if(index1.equals("gill") && index2.equals("tablespoon")) {outputNum = inputNum * 8;}
            else if(index1.equals("gill") && index2.equals("fluid ounce")) {outputNum = inputNum * 4;}
            else if(index1.equals("teaspoon") && index2.equals("gill")) {outputNum = inputNum * 0.0416666667;}
            else if(index1.equals("tablespoon") && index2.equals("gill")) {outputNum = inputNum * 0.125;}
            else if(index1.equals("fluid ounce") && index2.equals("gill")) {outputNum = inputNum * 0.25;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("cup") && index2.equals("teaspoon")) {outputNum = inputNum * 48;}
            else if(index1.equals("cup") && index2.equals("tablespoon")) {outputNum = inputNum * 16;}
            else if(index1.equals("cup") && index2.equals("fluid ounce")) {outputNum = inputNum * 8;}
            else if(index1.equals("cup") && index2.equals("gill")) {outputNum = inputNum * 2;}
            else if(index1.equals("teaspoon") && index2.equals("cup")) {outputNum = inputNum * 0.0208333;}
            else if(index1.equals("tablespoon") && index2.equals("cup")) {outputNum = inputNum * 0.0625;}
            else if(index1.equals("fluid ounce") && index2.equals("cup")) {outputNum = inputNum * 0.125;}
            else if(index1.equals("gill") && index2.equals("cup")) {outputNum = inputNum * 0.5;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("pint") && index2.equals("teaspoon")) {outputNum = inputNum * 96;}
            else if(index1.equals("pint") && index2.equals("tablespoon")) {outputNum = inputNum * 32;}
            else if(index1.equals("pint") && index2.equals("fluid ounce")) {outputNum = inputNum * 16;}
            else if(index1.equals("pint") && index2.equals("gill")) {outputNum = inputNum * 4;}
            else if(index1.equals("pint") && index2.equals("cup")) {outputNum = inputNum * 2;}
            else if(index1.equals("teaspoon") && index2.equals("pint")) {outputNum = inputNum * 0.0104167;}
            else if(index1.equals("tablespoon") && index2.equals("pint")) {outputNum = inputNum * 0.03125;}
            else if(index1.equals("fluid ounce") && index2.equals("pint")) {outputNum = inputNum * 0.0625;}
            else if(index1.equals("gill") && index2.equals("pint")) {outputNum = inputNum * 0.25;}
            else if(index1.equals("cup") && index2.equals("pint")) {outputNum = inputNum * 0.5;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("quart") && index2.equals("teaspoon")) {outputNum = inputNum * 192;}
            else if(index1.equals("quart") && index2.equals("tablespoon")) {outputNum = inputNum * 64;}
            else if(index1.equals("quart") && index2.equals("fluid ounce")) {outputNum = inputNum * 32;}
            else if(index1.equals("quart") && index2.equals("gill")) {outputNum = inputNum * 8;}
            else if(index1.equals("quart") && index2.equals("cup")) {outputNum = inputNum * 4;}
            else if(index1.equals("quart") && index2.equals("pint")) {outputNum = inputNum * 2;}
            else if(index1.equals("teaspoon") && index2.equals("quart")) {outputNum = inputNum * 0.00520833;}
            else if(index1.equals("tablespoon") && index2.equals("quart")) {outputNum = inputNum * 0.015625;}
            else if(index1.equals("fluid ounce") && index2.equals("quart")) {outputNum = inputNum * 0.03125;}
            else if(index1.equals("gill") && index2.equals("quart")) {outputNum = inputNum * 0.125;}
            else if(index1.equals("cup") && index2.equals("quart")) {outputNum = inputNum * 0.25;}
            else if(index1.equals("pint") && index2.equals("quart")) {outputNum = inputNum * 0.5;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("gallon") && index2.equals("teaspoon")) {outputNum = inputNum * 768;}
            else if(index1.equals("gallon") && index2.equals("tablespoon")) {outputNum = inputNum * 256;}
            else if(index1.equals("gallon") && index2.equals("fluid ounce")) {outputNum = inputNum * 128;}
            else if(index1.equals("gallon") && index2.equals("gill")) {outputNum = inputNum * 32;}
            else if(index1.equals("gallon") && index2.equals("cup")) {outputNum = inputNum * 16;}
            else if(index1.equals("gallon") && index2.equals("pint")) {outputNum = inputNum * 8;}
            else if(index1.equals("gallon") && index2.equals("quart")) {outputNum = inputNum * 4;}
            else if(index1.equals("teaspoon") && index2.equals("gallon")) {outputNum = inputNum * 0.00130208;}
            else if(index1.equals("tablespoon") && index2.equals("gallon")) {outputNum = inputNum * 0.00390625;}
            else if(index1.equals("fluid ounce") && index2.equals("gallon")) {outputNum = inputNum * 0.0078125;}
            else if(index1.equals("gill") && index2.equals("gallon")) {outputNum = inputNum * 0.03125;}
            else if(index1.equals("cup") && index2.equals("gallon")) {outputNum = inputNum * 0.0625;}
            else if(index1.equals("pint") && index2.equals("gallon")) {outputNum = inputNum * 0.125;}
            else if(index1.equals("quart") && index2.equals("gallon")) {outputNum = inputNum * 0.25;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("milliliter") && index2.equals("teaspoon")) {outputNum = inputNum * 0.202884;}
            else if(index1.equals("milliliter") && index2.equals("tablespoon")) {outputNum = inputNum * 0.067628;}
            else if(index1.equals("milliliter") && index2.equals("fluid ounce")) {outputNum = inputNum * 0.33814;}
            else if(index1.equals("milliliter") && index2.equals("gill")) {outputNum = inputNum * 0.00845350568;}
            else if(index1.equals("milliliter") && index2.equals("cup")) {outputNum = inputNum * 0.00422675;}
            else if(index1.equals("milliliter") && index2.equals("pint")) {outputNum = inputNum * 0.00211338;}
            else if(index1.equals("milliliter") && index2.equals("quart")) {outputNum = inputNum * 0.00105669;}
            else if(index1.equals("milliliter") && index2.equals("gallon")) {outputNum = inputNum * 0.000264172;}
            else if(index1.equals("teaspoon") && index2.equals("milliliter")) {outputNum = inputNum * 4.92892;}
            else if(index1.equals("tablespoon") && index2.equals("milliliter")) {outputNum = inputNum * 14.7868;}
            else if(index1.equals("fluid ounce") && index2.equals("milliliter")) {outputNum = inputNum * 29.5735;}
            else if(index1.equals("gill") && index2.equals("milliliter")) {outputNum = inputNum * 118.294118;}
            else if(index1.equals("cup") && index2.equals("milliliter")) {outputNum = inputNum * 236.588;}
            else if(index1.equals("pint") && index2.equals("milliliter")) {outputNum = inputNum * 473.176;}
            else if(index1.equals("quart") && index2.equals("milliliter")) {outputNum = inputNum * 946.353;}
            else if(index1.equals("gallon") && index2.equals("milliliter")) {outputNum = inputNum * 3785.41;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("liter") && index2.equals("teaspoon")) {outputNum = inputNum * 202.884;}
            else if(index1.equals("liter") && index2.equals("tablespoon")) {outputNum = inputNum * 67.628;}
            else if(index1.equals("liter") && index2.equals("fluid ounce")) {outputNum = inputNum * 33.814;}
            else if(index1.equals("liter") && index2.equals("gill")) {outputNum = inputNum * 8.45350568;}
            else if(index1.equals("liter") && index2.equals("cup")) {outputNum = inputNum * 4.22675;}
            else if(index1.equals("liter") && index2.equals("pint")) {outputNum = inputNum * 2.11338;}
            else if(index1.equals("liter") && index2.equals("quart")) {outputNum = inputNum * 1.05669;}
            else if(index1.equals("liter") && index2.equals("gallon")) {outputNum = inputNum * 0.264172;}
            else if(index1.equals("liter") && index2.equals("milliliter")) {outputNum = inputNum * 1000;}
            else if(index1.equals("teaspoon") && index2.equals("liter")) {outputNum = inputNum * 0.00492892;}
            else if(index1.equals("tablespoon") && index2.equals("liter")) {outputNum = inputNum * 0.0147868;}
            else if(index1.equals("fluid ounce") && index2.equals("liter")) {outputNum = inputNum * 0.0295735;}
            else if(index1.equals("gill") && index2.equals("liter")) {outputNum = inputNum * 0.118294118;}
            else if(index1.equals("cup") && index2.equals("liter")) {outputNum = inputNum * 0.236588;}
            else if(index1.equals("pint") && index2.equals("liter")) {outputNum = inputNum * 0.473176;}
            else if(index1.equals("quart") && index2.equals("liter")) {outputNum = inputNum * 0.946353;}
            else if(index1.equals("gallon") && index2.equals("liter")) {outputNum = inputNum * 3.78541;}
            else if(index1.equals("milliliter") && index2.equals("liter")) {outputNum = inputNum * 0.001;}
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if(index1.equals("deciliter") && index2.equals("teaspoon")) {outputNum = inputNum * 20.2884136;}
            else if(index1.equals("deciliter") && index2.equals("tablespoon")) {outputNum = inputNum * 6.76280454;}
            else if(index1.equals("deciliter") && index2.equals("fluid ounce")) {outputNum = inputNum * 3.38140227;}
            else if(index1.equals("deciliter") && index2.equals("gill")) {outputNum = inputNum * 0.845350568;}
            else if(index1.equals("deciliter") && index2.equals("cup")) {outputNum = inputNum * 0.422675284;}
            else if(index1.equals("deciliter") && index2.equals("pint")) {outputNum = inputNum * 0.211337642;}
            else if(index1.equals("deciliter") && index2.equals("quart")) {outputNum = inputNum * 0.105668821;}
            else if(index1.equals("deciliter") && index2.equals("gallon")) {outputNum = inputNum * 0.0264172052;}
            else if(index1.equals("deciliter") && index2.equals("milliliter")) {outputNum = inputNum * 100;}
            else if(index1.equals("deciliter") && index2.equals("liter")) {outputNum = inputNum * 0.1;}
            else if(index1.equals("teaspoon") && index2.equals("deciliter")) {outputNum = inputNum * 0.0492892159;}
            else if(index1.equals("tablespoon") && index2.equals("deciliter")) {outputNum = inputNum * 0.147867648;}
            else if(index1.equals("fluid ounce") && index2.equals("deciliter")) {outputNum = inputNum * 0.295735296;}
            else if(index1.equals("gill") && index2.equals("deciliter")) {outputNum = inputNum * 1.18294118;}
            else if(index1.equals("cup") && index2.equals("deciliter")) {outputNum = inputNum * 2.36588237;}
            else if(index1.equals("pint") && index2.equals("deciliter")) {outputNum = inputNum * 4.73176473;}
            else if(index1.equals("quart") && index2.equals("deciliter")) {outputNum = inputNum * 9.46352946;}
            else if(index1.equals("gallon") && index2.equals("deciliter")) {outputNum = inputNum * 37.8541178;}
            else if(index1.equals("milliliter") && index2.equals("deciliter")) {outputNum = inputNum * 0.01;}
            else if(index1.equals("liter") && index2.equals("deciliter")) {outputNum = inputNum * 10;}

            output.setText(Double.toString(outputNum));
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Try Again... please", Toast.LENGTH_SHORT);
            toast.show();
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
        final Button teaspoon1 = new Button(ConvertVolume.this);
        final Button tablespoon1 = new Button(ConvertVolume.this);
        final Button fluidOunce1 = new Button(ConvertVolume.this);
        final Button gills1 = new Button(ConvertVolume.this);
        final Button cups1 = new Button (ConvertVolume.this);
        final Button pint1 = new Button (ConvertVolume.this);
        final Button quart1 = new Button (ConvertVolume.this);
        final Button gallon1 = new Button (ConvertVolume.this);
        final Button milliliter1 = new Button (ConvertVolume.this);
        final Button liter1 = new Button (ConvertVolume.this);
        final Button deciliter1 = new Button (ConvertVolume.this);

        //set the name to the buttons
        teaspoon1.setText("teaspoons");
        tablespoon1.setText("tablespoons");
        fluidOunce1.setText("fluid ounces");
        gills1.setText("gills");
        cups1.setText("cups");
        pint1.setText("pints");
        quart1.setText("quarts");
        gallon1.setText("gallons");
        liter1.setText("liters");
        milliliter1.setText("milliliters");
        liter1.setText("liters");
        deciliter1.setText("deciliters");


        //all the on click listeners...
        teaspoon1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "teaspoon";
                itemSelect1.setText("teaspoons");
            }
        });

        tablespoon1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "tablespoon";
                itemSelect1.setText("tablespoons");

            }
        });

        fluidOunce1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "fluid ounce";
                itemSelect1.setText("fluid ounces");

            }
        });

        gills1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "gill";
                itemSelect1.setText("gills");
            }
        });

        cups1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "cup";
                itemSelect1.setText("cups");
            }
        });
        pint1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "pint";
                itemSelect1.setText("pints");
            }
        });
        quart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
                index1 = "quart";
                itemSelect1.setText("quarts");
            }
        });
        gallon1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "gallon";
                itemSelect1.setText("gallons");
            }
        });
        milliliter1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "milliliter";
                itemSelect1.setText("milliliters");
            }
        });
        liter1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "liter";
                itemSelect1.setText("liters");
            }
        });
        deciliter1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "deciliter";
                itemSelect1.setText("deciliters");
            }
        });



        //NOW ADD THE STUFF CREATED ABOVE TO THE VIEW WITH A NEW LAYOUT

        layout1 = new LinearLayout(ConvertVolume.this);
        layout1.setOrientation(LinearLayout.VERTICAL);
        //Add the buttons to the layout
        layout1.addView(teaspoon1);
        layout1.addView(tablespoon1);
        layout1.addView(fluidOunce1);
        layout1.addView(gills1);
        layout1.addView(cups1);
        layout1.addView(pint1);
        layout1.addView(quart1);
        layout1.addView(gallon1);
        layout1.addView(milliliter1);
        layout1.addView(liter1);
        layout1.addView(deciliter1);
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
        final Button teaspoons2 = new Button(ConvertVolume.this);
        final Button tablespoons2 = new Button(ConvertVolume.this);
        final Button fluidOunces2 = new Button(ConvertVolume.this);
        final Button gills2 = new Button(ConvertVolume.this);
        final Button cups2 = new Button(ConvertVolume.this);
        final Button pint2 = new Button (ConvertVolume.this);
        final Button quart2 = new Button (ConvertVolume.this);
        final Button gallon2 = new Button (ConvertVolume.this);
        final Button milliliter2 = new Button (ConvertVolume.this);
        final Button liter2 = new Button (ConvertVolume.this);
        final Button deciliter2 = new Button (ConvertVolume.this);

        //set the name to the buttons
        teaspoons2.setText("teaspoons");
        tablespoons2.setText("tablespoons");
        fluidOunces2.setText("fluid ounces");
        gills2.setText("gills");
        cups2.setText("cups");
        pint2.setText("pints");
        quart2.setText("quarts");
        gallon2.setText("gallons");
        liter2.setText("liters");
        milliliter2.setText("milliliters");
        liter2.setText("liters");
        deciliter2.setText("deciliters");

        //all the on click listeners...
        teaspoons2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "teaspoon";
                itemSelect2.setText("teaspoons");
            }
        });

        tablespoons2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "tablespoon";
                itemSelect2.setText("tablespoons");

            }
        });

        fluidOunces2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "fluid ounce";
                itemSelect2.setText("fluid ounces");

            }
        });

        gills2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "gill";
                itemSelect2.setText("gills");
            }
        });

        cups2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "cup";
                itemSelect2.setText("cups");
            }
        });
        pint2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "pint";
                itemSelect2.setText("pints");
            }
        });
        quart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.dismiss();
                index2 = "quart";
                itemSelect2.setText("quarts");
            }
        });
        gallon2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "gallon";
                itemSelect2.setText("gallons");
            }
        });
        milliliter2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "milliliter";
                itemSelect2.setText("milliliters");
            }
        });
        liter2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "liter";
                itemSelect2.setText("liters");
            }
        });
        deciliter2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "deciliter";
                itemSelect2.setText("deciliters");
            }
        });

        layout2 = new LinearLayout(ConvertVolume.this);
        layout2.setOrientation(LinearLayout.VERTICAL);
        //Add the buttons to the layout
        layout2.addView(teaspoons2);
        layout2.addView(tablespoons2);
        layout2.addView(fluidOunces2);
        layout2.addView(gills2);
        layout2.addView(cups2);
        layout2.addView(pint2);
        layout2.addView(quart2);
        layout2.addView(gallon2);
        layout2.addView(milliliter2);
        layout2.addView(liter2);
        layout2.addView(deciliter2);
        //now set the view and create it
        b.setView(layout2);
        bd = b.create();
        bd = b.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_convert_volume, menu);
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
            Intent i = new Intent(ConvertVolume.this, Settings.class);
            startActivity(i);
            return true;
        }
        //TODO: add the UI elements for this activity
        /*else if(id == R.id.mass)
        {
            Intent i = new Intent(this, ConvertMass.class);
            startActivity(i);
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
