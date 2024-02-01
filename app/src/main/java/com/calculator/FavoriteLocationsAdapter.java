package com.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoriteLocationsAdapter extends ArrayAdapter<Location> {
    private ArrayList<Location> mFavoriteLocationsArray;
    private Context context;

    public FavoriteLocationsAdapter(@NonNull Context context, ArrayList<Location> mFavoriteLocationsArray) {
        super(context, R.layout.favorite_location, mFavoriteLocationsArray);
        this.mFavoriteLocationsArray = mFavoriteLocationsArray;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.favorite_location, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.favoriteLocationNameTextView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String locationNameString = mFavoriteLocationsArray.get(position).locationName;
        viewHolder.textView.setText(locationNameString);

        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
    }
}
