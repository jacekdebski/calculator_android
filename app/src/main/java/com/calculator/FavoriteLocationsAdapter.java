package com.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoriteLocationsAdapter extends RecyclerView.Adapter<FavoriteLocationsAdapter.FavoriteLocationsViewHolder> {
    private ArrayList<Location> mFavoriteLocationsArray;


    public FavoriteLocationsAdapter(ArrayList<Location> favoriteLocationsArray) {
        mFavoriteLocationsArray = favoriteLocationsArray;
    }

    @NonNull
    @Override
    public FavoriteLocationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_location, parent, false);
        return new FavoriteLocationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteLocationsAdapter.FavoriteLocationsViewHolder holder, int position) {
        Location location = mFavoriteLocationsArray.get(position);
        holder.bind(location);
    }

    @Override
    public int getItemCount() {
        return mFavoriteLocationsArray.size();
    }

    public class FavoriteLocationsViewHolder extends RecyclerView.ViewHolder {
        private TextView favoriteLocationTextView;

        public FavoriteLocationsViewHolder(View itemView) {
            super(itemView);
            favoriteLocationTextView = itemView.findViewById(R.id.favoriteLocationNameTextView);
        }

        public void bind(Location location) {
            favoriteLocationTextView.setText(location.locationName);
        }
    }
}
