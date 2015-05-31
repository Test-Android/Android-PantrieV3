package com.nicodangelo.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nicodangelo.database.Item;
import com.nicodangelo.pantrie.R;

import java.util.ArrayList;

public class DefaultTableAdapter extends ArrayAdapter
{
    private ArrayList<Item> items;
    public DefaultTableAdapter(Context context, int resource, ArrayList<Item> items)
    {
        super(context, resource, items);
        this.items = items;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if(v == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.default_table_adapter, null);
        }
        Item i = items.get(position);
        TextView itemName = (TextView) v.findViewById(R.id.itemName);
        TextView itemAmount = (TextView) v.findViewById(R.id.itemAmount);

        //itemName.setText(i.getName());
        //itemAmount.setText(i.getAmount());

        return v;

    }
}
