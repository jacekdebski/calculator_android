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
    private static WeatherInfoLoadListener mWeatherInfoLoadListener;
    private static WeatherInfoData mWeatherInfoData;
    private static Context mContext;
    private static ArrayList<Location> mFavoriteLocationsArray;

    public static void init(Context context) {
        Log.i("WeatherInfoManager", "Constructor");

        mContext = context;
        mWeatherInfoAPIController = new WeatherInfoAPIController(mContext);

        mWeatherInfoAPIController.setWeatherInfoFetchDataListener(new WeatherInfoFetchDataListener() {

            @Override
            public void onFetchWeatherInfo(WeatherInfoData weatherInfoData) {
                mWeatherInfoData = weatherInfoData;
                saveWeatherInfoDataToSharedPreferences(weatherInfoData);
                Toast.makeText(mContext, "weather data updated", Toast.LENGTH_SHORT).show();
                mWeatherInfoLoadListener.onLoadWeatherInfo();
            }
        });

        mWeatherInfoData = loadWeatherInfoDataFromSharedPreferences();

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

    private static void saveWeatherInfoDataToSharedPreferences(WeatherInfoData weatherInfoData) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("WeatherDataFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("locationName", weatherInfoData.location.locationName);
        editor.putFloat("latitude", weatherInfoData.location.geographicalCoordinates.latitude);
        editor.putFloat("longitude", weatherInfoData.location.geographicalCoordinates.longitude);
        editor.putString("weatherDescription", weatherInfoData.weatherDescription);
        editor.putLong("timeOfDataCalculation", weatherInfoData.timeOfDataCalculation.toInstant().getEpochSecond());
        editor.putFloat("temperature", weatherInfoData.temperature);
        editor.putInt("pressure", weatherInfoData.pressure);
        editor.putFloat("windSpeed", weatherInfoData.windSpeed);
        editor.putFloat("windDirection", weatherInfoData.windDirection);
        editor.putInt("humidity", weatherInfoData.humidity);
//        editor.apply(); //it is asynchronous
        editor.commit();
    }

    private static WeatherInfoData loadWeatherInfoDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("WeatherDataFile", Context.MODE_PRIVATE);
        String locationName = sharedPreferences.getString("locationName", "no data");
        Float latitude = sharedPreferences.getFloat("latitude", 0);
        Float longitude = sharedPreferences.getFloat("longitude", 0);
        GeographicalCoordinates geographicalCoordinates = new GeographicalCoordinates(latitude, longitude);
        Location location = new Location(geographicalCoordinates, locationName);
        String weatherDescription = sharedPreferences.getString("weatherDescription", "no data");
        String weatherIcon = sharedPreferences.getString("weatherIcon", "no data");
        Long unixTimestamp = sharedPreferences.getLong("timeOfDataCalculation", 0);
        ZonedDateTime timeOfDataCalculation = Instant.ofEpochSecond(unixTimestamp).atZone(ZoneId.of("UTC"));
        Log.i("WeatherInfoManager", "loaded time of data calculation: " + timeOfDataCalculation);
        float temperature = sharedPreferences.getFloat("temperature", 0);
        int pressure = sharedPreferences.getInt("pressure", 0);
        float windSpeed = sharedPreferences.getFloat("windSpeed", 0);
        float windDirection = sharedPreferences.getFloat("windDirection", 0);
        int humidity = sharedPreferences.getInt("humidity", 0);
        return new WeatherInfoData(location, weatherDescription, weatherIcon, timeOfDataCalculation, temperature, pressure, windSpeed, windDirection, humidity);
    }
}
