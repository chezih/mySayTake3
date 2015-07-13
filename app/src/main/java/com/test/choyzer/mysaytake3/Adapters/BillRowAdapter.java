package com.test.choyzer.mysaytake3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.test.choyzer.mysaytake3.Model.Entities.Bill;
import com.test.choyzer.mysaytake3.R;

import java.util.ArrayList;

/**
 * Created by Choyzer on 13/07/2015.
 */
public class BillRowAdapter extends ArrayAdapter<Bill> {

    public BillRowAdapter(Context context, ArrayList<Bill> bills) {
        super(context, 0, bills);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Bill bill = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bill_row_template, parent, false);
        }
        // Lookup view for data population
        TextView billSubjectTextView = (TextView) convertView.findViewById(R.id.billSubjectTextView);
        TextView billDescriptionTextView = (TextView) convertView.findViewById(R.id.billDescriptionTextView);
        // Populate the data into the template view using the data object
        billSubjectTextView.setText(bill.getSubject());
        billDescriptionTextView.setText(bill.getDescription());
        // Return the completed view to render on screen
        return convertView;
    }


}
