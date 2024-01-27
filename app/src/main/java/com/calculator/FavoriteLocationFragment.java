package com.calculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavoriteLocationFragment extends Fragment {

    private static final String LOCATION = "";
    private static final String LATITUDE = "";
    private static final String LONGITUDE = "";

    private String mLocation;
    private double mLatitude;
    private double mLongitude;

    public FavoriteLocationFragment() {
    }

    public static FavoriteLocationFragment newInstance(Location favoriteLocation) {
        FavoriteLocationFragment fragment = new FavoriteLocationFragment();
        Bundle args = new Bundle();
        args.putString(LOCATION, favoriteLocation.location);
        args.putDouble(String.valueOf(LATITUDE), favoriteLocation.geographicalCoordinates.latitude);
        args.putDouble(String.valueOf(LONGITUDE), favoriteLocation.geographicalCoordinates.longitude);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLocation = getArguments().getString(LOCATION);
            mLatitude = Double.parseDouble(LATITUDE);
            mLongitude = Double.parseDouble(LONGITUDE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_location, container, false);
    }
}