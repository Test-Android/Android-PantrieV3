//@author jake Cox

package com.nicodangelo.database;

import android.provider.ContactsContract;

public class Item
{
    private int id;
    private String name;
    private int amount;
    private int low_amount;
    private String created_at;

    //constructors
    public Item(){}

    public Item(int id)
    {
        this.id = id;
    }

    public Item(int id, String name)
    {
        this.id = id;
        this.name = name;

        this.setAmount(0);
        this.setLow_amount(0);
        this.setCreated_at(DatabaseHandler.getDateAndTime());
    }

    public Item(int id, String name, int amount)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;

        this.setLow_amount(0);
        this.setCreated_at(DatabaseHandler.getDateAndTime());
    }

    public Item(int low_amount, int id, String name, int amount)
    {
        this.low_amount = low_amount;
        this.id = id;
        this.name = name;
        this.amount = amount;

        this.setCreated_at(DatabaseHandler.getDateAndTime());
    }

    public Item(String created_at, int id, String name, int amount, int low_amount)
    {
        this.created_at = created_at;
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.low_amount = low_amount;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public int getLow_amount()
    {
        return low_amount;
    }

    public void setLow_amount(int low_amount)
    {
        this.low_amount = low_amount;
    }

    public String getCreated_at()
    {
        return created_at;
    }

    public void setCreated_at(String created_at)
    {
        this.created_at = created_at;
    }
}
