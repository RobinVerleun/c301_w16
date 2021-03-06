package com.example.c301_w16_g5.c301_w16_g5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class provides the adapter for allowing chickens to be displayed in
 * ListViews.
 *
 * @author  Hailey
 * @version 1.4, 03/16/2016
 * @see     Chicken
 * @see     ChickenViewsActivity
 * @see     SearchesActivity
 */
public class ChickenAdapter extends ArrayAdapter<Chicken> {
    public ChickenAdapter(Context context, ArrayList<Chicken> chickens) {
        super(context, 0, chickens);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Chicken chicken = getItem(position);
        String name = chicken.getName();
        String description = chicken.getDescription();
        String status = chicken.getChickenStatus().toString();

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chicken, parent, false);
        }

        TextView chickenName = (TextView) convertView.findViewById(R.id.chickenName);
        TextView chickenDescription = (TextView) convertView.findViewById(R.id.chickenDescription);
        TextView chickenStatus = (TextView) convertView.findViewById(R.id.chickenStatus);

        // Populate the data into the template view using the data object

        chickenName.setText(name);
        chickenDescription.setText(description);
        chickenStatus.setText(status);

        // Return the completed view to render on screen
        return convertView;
    }
}
