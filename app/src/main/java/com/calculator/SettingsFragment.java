package com.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    FavoriteLocationsAdapter favoriteLocationsAdapter;

    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button setLocationButton = view.findViewById(R.id.setLocationButton);
        setLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText locationEditText = (EditText) view.findViewById(R.id.locationEditText);
                String location = locationEditText.getText().toString();
                WeatherInfoManager.setLocation(location);
            }
        });

        Button addToFavoriteLocationButton = view.findViewById(R.id.addToFavoriteLocationButton);
        addToFavoriteLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Settings Fragment", WeatherInfoManager.getWeatherInfoData().location.locationName);
                WeatherInfoManager.addLocationToFavorite(WeatherInfoManager.getWeatherInfoData().location);
                favoriteLocationsAdapter.notifyDataSetChanged();
            }
        });

        Button refreshDataButton = view.findViewById(R.id.refreshDataButton);
        refreshDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherInfoManager.refreshWeatherInfoData();
            }
        });

        ArrayList<Location> favoriteLocationsArray = WeatherInfoManager.getFavoriteLocationsArray();

        favoriteLocationsAdapter = new FavoriteLocationsAdapter(getContext().getApplicationContext(), favoriteLocationsArray);
        ListView listView = view.findViewById(R.id.favoriteLocationsListView);
        listView.setAdapter(favoriteLocationsAdapter);
    }
}