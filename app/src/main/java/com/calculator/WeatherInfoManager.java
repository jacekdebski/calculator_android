package com.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;
import android.widget.Toast;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class WeatherInfoManager {
    private static WeatherInfoAPIController mWeatherInfoAPIController;
    private static FileOperationsController mFileOperationsController;
    private static WeatherInfoLoadListener mWeatherInfoLoadListener;
    private static WeatherInfoData mWeatherInfoData;
    private static Context mContext;
    private static ArrayList<Location> mFavoriteLocationsArray;

    public static void init(Context context) {
        Log.i("WeatherInfoManager", "Constructor");

        mContext = context;
        mWeatherInfoAPIController = new WeatherInfoAPIController(mContext);
        mFileOperationsController = new FileOperationsController(mContext);

        mWeatherInfoAPIController.setWeatherInfoFetchDataListener(new WeatherInfoFetchDataListener() {

            @Override
            public void onFetchWeatherInfo(WeatherInfoData weatherInfoData) {
                mWeatherInfoData = weatherInfoData;
                mFileOperationsController.saveWeatherInfoDataToSharedPreferences(weatherInfoData);
                Toast.makeText(mContext, "weather data updated", Toast.LENGTH_SHORT).show();
                mWeatherInfoLoadListener.onLoadWeatherInfo();
            }
        });

        mWeatherInfoData = mFileOperationsController.loadWeatherInfoDataFromSharedPreferences();

        if (mWeatherInfoData.location.locationName.equals("no data")) {
            Log.i("WeatherInfoManager", "locationName: " + mWeatherInfoData.location.locationName);
            mWeatherInfoData.location.locationName = "Warsaw";
        }
        refreshWeatherInfoData();
        mFavoriteLocationsArray = new ArrayList<Location>(); // TODO: load from file or create empty
    }

    public static WeatherInfoData getWeatherInfoData() {
        return mWeatherInfoData;
    }

    public static ArrayList<Location> getFavoriteLocationsArray() {
        return mFavoriteLocationsArray;
    }

    public static void addLocationToFavorite(Location location) {

        if (!mFavoriteLocationsArray.contains(location)) {
            mFavoriteLocationsArray.add(location);
        } else {
            Toast.makeText(mContext, location.locationName + " is already in the favorites", Toast.LENGTH_SHORT).show();
        }
    }

    public static void removeLocationFromFavorite(String locationName) {
        for (Location location : mFavoriteLocationsArray) {
            if (location.locationName.equals(locationName)) {
                mFavoriteLocationsArray.remove(location);
                Toast.makeText(mContext, location.locationName + " has been removed from the favorites", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public static void setWeatherInfoLoadListener(WeatherInfoLoadListener listener) {
        mWeatherInfoLoadListener = listener;
    }

    private static void tryToFetchWeatherInfoData(String location) {
        if (checkInternetConnection()) {
            mWeatherInfoAPIController.fetchGeographicalCoordinates(location);
        } else {
            Log.i("WeatherInfoManager", "unable to fetch weather data due to lack of internet connection");
            Toast.makeText(mContext, "unable to fetch weather data due to lack of internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public static void refreshWeatherInfoData() {
        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        Log.i("WeatherInfoManager", "currentDateTime: " + currentDateTime);
        Duration duration = Duration.between(currentDateTime, mWeatherInfoData.timeOfDataCalculation);
        if (duration.abs().compareTo(Duration.ofHours(1)) > 0) {
            Log.i("WeatherInfoManager", "refresh weather data");
            tryToFetchWeatherInfoData(mWeatherInfoData.location.locationName);
        } else {
            Log.i("WeatherInfoManager", "no need to update weather data: time of data calculation: " + mWeatherInfoData.timeOfDataCalculation);
            Toast.makeText(mContext, "weather data is up to date, no need to update", Toast.LENGTH_SHORT).show();
        }
    }

    public static void setLocation(String locationName) {
        Log.i("WeatherInfoManager", locationName + " " + mWeatherInfoData.location.locationName);
        if (locationName.equals(mWeatherInfoData.location.locationName)) {
            Toast.makeText(mContext, locationName + " is already set", Toast.LENGTH_SHORT).show();
        } else if (locationName.equals("")) {
            Toast.makeText(mContext, "The location name may not be empty", Toast.LENGTH_SHORT).show();
        } else {
            tryToFetchWeatherInfoData(locationName);
        }
    }

    private static boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isInternetConnection = false;

        if (connectivityManager != null) {
            Network network = connectivityManager.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                if (networkCapabilities != null) {
                    isInternetConnection = (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
                }
            }
        } else {
            Log.i("WeatherInfoManager", "connectivityManager is NULL");
        }
        return isInternetConnection;
    }

}
