package com.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
                Log.i("Settings Fragment", WeatherInfoManager.getWeatherInfoData().location.locationName);
                WeatherInfoManager.addLocationToFavorite(WeatherInfoManager.getWeatherInfoData().location);
            }
        });

//        ArrayList<FavoriteLocationFragment> favoriteLocationFragmentArrayList = new ArrayList<>();

//        ViewPager viewPager = view.findViewById(R.id.favoritePlacesViewPager);

//        for (int i = 0; i < favoriteLocationsArray.size(); i++) {
//            Location favoriteLocation = favoriteLocationsArray.get(i);
//            favoriteLocationFragmentArrayList.add(FavoriteLocationFragment.newInstance(favoriteLocation));
//        }

        ArrayList<Location> favoriteLocationsArray = WeatherInfoManager.getFavoriteLocationsArray();

        //        //start debug
        GeographicalCoordinates geo = new GeographicalCoordinates(3,43);
        Location location = new Location(geo, "testLocation2");
        WeatherInfoManager.addLocationToFavorite(location);

        Log.i("Settings Fragment", String.valueOf(favoriteLocationsArray.size()));
//        //end debug

        RecyclerView favoriteLocationsRecyclerView = view.findViewById(R.id.favoriteLocationsRecyclerView);
        favoriteLocationsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FavoriteLocationsAdapter favoriteLocationsAdapter =  new FavoriteLocationsAdapter(favoriteLocationsArray);
        favoriteLocationsRecyclerView.setAdapter(favoriteLocationsAdapter);


        Location location2 = new Location(geo, "testLocation3");
        WeatherInfoManager.addLocationToFavorite(location2);
        favoriteLocationsAdapter.notifyDataSetChanged();

    }
}