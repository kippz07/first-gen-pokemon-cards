package com.kippz.jenny.firstgen;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jenny on 07/10/2016.
 */

public class CardSetAdapter extends ArrayAdapter<CardSet>{


    public CardSetAdapter(Activity context, ArrayList<CardSet> set) {
        //Initialize the ArrayAdapter's internal storage for the context and the list
        super(context, 0, set);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if View is being used, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }

        //Get object located at this position in list
        CardSet currentSet = getItem(position);

        //Find the TextView its the right ID name
        TextView setName = (TextView) listItemView.findViewById(R.id.set_name);

        //Use the getter to set the name
        setName.setText(currentSet.getSetName());

        return listItemView;
    }
}
