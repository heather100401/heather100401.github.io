package com.example.weighttracker;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class WeightAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> weights;
    private ArrayList<String> dates;

    public WeightAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.weights = new ArrayList<>();
        this.dates = new ArrayList<>();

        // Extract data from cursor
        while (cursor.moveToNext()) {
            dates.add(cursor.getString(0));  // Date column
            weights.add(cursor.getString(1)); // Weight column
        }
    }

    @Override
    public int getCount() {
        return weights.size();
    }

    @Override
    public Object getItem(int position) {
        return weights.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_weight, parent, false);
        }

        TextView dateTextView = convertView.findViewById(R.id.dateTextView);
        TextView weightTextView = convertView.findViewById(R.id.weightTextView);

        dateTextView.setText(dates.get(position));
        weightTextView.setText(weights.get(position) + " lbs");

        return convertView;
    }
}
