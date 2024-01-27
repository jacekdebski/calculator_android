package com.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
                WeatherInfoManager.tryToFetchWeatherInfoData(location);
            }
        });

        Button addToFavoriteLocationButton = view.findViewById(R.id.addToFavoriteLocationButton);
        addToFavoriteLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherInfoManager.addLocationToFavorite(WeatherInfoManager.getWeatherInfoData().location);
            }
        });

        ArrayList<FavoriteLocationFragment> favoriteLocationFragmentArrayList = new ArrayList<>();
        ArrayList<Location> favoriteLocationsArray = WeatherInfoManager.getFavoriteLocationsArray();
        ViewPager viewPager = view.findViewById(R.id.favoritePlacesViewPager);

        for (int i = 0; i < favoriteLocationsArray.size(); i++) {
            Location favoriteLocation = favoriteLocationsArray.get(i);
            favoriteLocationFragmentArrayList.add(FavoriteLocationFragment.newInstance(favoriteLocation));
        }

        FavoriteLocationsAdapter favoriteLocationsAdapter = new FavoriteLocationsAdapter(getActivity().getSupportFragmentManager(), favoriteLocationFragmentArrayList);

        viewPager.setAdapter(favoriteLocationsAdapter);
    }
}