package com.example.jetts.listviewtestything;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String>
{
    private Context context;
    private final ArrayList<String> values;

    public ListAdapter(Context context, ArrayList<String> values)
    {
        super(context, R.layout.activity_list_adapter,values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list_adapter, parent, false);
        TextView itemNameTextView = (TextView) rowView.findViewById(R.id.itemNameTextView);

        itemNameTextView.setText(values.get(position));
        TextView itemAmountTextView = (TextView) rowView.findViewById(R.id.itemAmountTextView);
        itemAmountTextView.setText(values.get(position));
        return rowView;
    }
    public void textTest(View view)
    {

    }

}
