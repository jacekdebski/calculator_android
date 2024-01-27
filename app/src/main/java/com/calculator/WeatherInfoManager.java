package com.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeatherInfoManager {
    private static WeatherInfoAPIController mWeatherInfoAPIController;
    private static WeatherInfoLoadListener mWeatherInfoLoadListener;
    private static WeatherInfoData mWeatherInfoData;
    private static Context mContext;
    private static ArrayList<FavoriteLocation> mFavoriteLocationsArray;

    public static void init(Context context) {
        Log.i("WeatherInfoManager", "Constructor");

        mContext = context;
        mWeatherInfoAPIController = new WeatherInfoAPIController(mContext);

        mWeatherInfoAPIController.setWeatherInfoFetchDataListener(new WeatherInfoFetchDataListener() {

            @Override
            public void onFetchWeatherInfo(WeatherInfoData weatherInfoData) {
                setWeatherInfoData(weatherInfoData);
                saveWeatherInfoDataToSharedPreferences(weatherInfoData);
                mWeatherInfoLoadListener.onLoadWeatherInfo();
            }
        });

        mWeatherInfoData = loadWeatherInfoDataFromSharedPreferences();
        mFavoriteLocationsArray = new ArrayList<FavoriteLocation>(); // TODO: load from file or create empty
    }

    public static WeatherInfoData getWeatherInfoData() {
        return mWeatherInfoData;
    }

    public static ArrayList<FavoriteLocation> getFavoriteLocationsArray() {
        return mFavoriteLocationsArray;
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

    private static void setWeatherInfoData(WeatherInfoData weatherInfoData) {
        mWeatherInfoData = weatherInfoData;
    }

    private boolean checkInternetConnection() {
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

    private static void saveWeatherInfoDataToSharedPreferences(WeatherInfoData weatherInfoData) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("WeatherDataFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("weatherMain", weatherInfoData.weatherMain);
        editor.putString("weatherDescription", weatherInfoData.weatherDescription);
        editor.putString("weatherDescription", weatherInfoData.weatherDescription);
//        editor.apply(); //it is asynchronous
        editor.commit();
    }

    private static WeatherInfoData loadWeatherInfoDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("WeatherDataFile", Context.MODE_PRIVATE);
        String weatherMain = sharedPreferences.getString("weatherMain", "no data");
        String weatherDescription = sharedPreferences.getString("weatherDescription", "no data");
        String weatherIcon = sharedPreferences.getString("weatherIcon", "no data");
        Log.i("WeatherInfoManager", weatherMain + " " + weatherDescription + " " + weatherIcon);
        return new WeatherInfoData(weatherMain, weatherDescription, weatherIcon);
    }
}
