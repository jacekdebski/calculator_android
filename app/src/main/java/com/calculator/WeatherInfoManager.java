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
        mFavoriteLocationsArray.add(location);
    }

    public static void removeLocationFromFavorite(String locationName) {
        for (Location location : mFavoriteLocationsArray) {
            if (location.locationName.equals(locationName)) {
                mFavoriteLocationsArray.remove(location);
                break;
            }
        }
    }

    public static void setWeatherInfoLoadListener(WeatherInfoLoadListener listener) {
        mWeatherInfoLoadListener = listener;
    }

    public static void tryToFetchWeatherInfoData(String location) {
        mWeatherInfoAPIController.fetchGeographicalCoordinates(location);
//        if (checkInternetConnection()) {
//            this.weatherInfoAPIController.fetchWeatherInfo();
////        this.weatherInfoAPIController.fetchGeographicalCoordinates();
//        } else {
//            WeatherInfoData weatherInfoData = loadWeatherInfoDataFromSharedPreferences();
////            Log.i("WeatherInfoManager", "in getWeatherInfo after loadWeatherInfoData" + this.weatherInfoData.weatherMain);
//            Log.i("WeatherInfoManager", "in getWeatherInfo after loadWeatherInfoData");
//            setWeatherInfoData(weatherInfoData);
//            Log.i("WeatherInfoManager", "in getWeatherInfo after setWeatherInfoData" + this.weatherInfoData.weatherMain);
//            weatherInfoLoadListener.onLoadWeatherInfo();
//            Log.i("WeatherInfoManager", "no internet connection");
//        }
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
