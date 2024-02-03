package com.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class FileOperationsController {
    private Context mContext;

    FileOperationsController(Context context) {
        this.mContext = context;
    }

    public void saveWeatherInfoDataToSharedPreferences(WeatherInfoData weatherInfoData) {
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
        editor.putBoolean("isCelsius", weatherInfoData.isCelsiusUnit);
//        editor.apply(); //it is asynchronous
        editor.commit();
    }

    public WeatherInfoData loadWeatherInfoDataFromSharedPreferences() {
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
        boolean isCelsiusUnit = sharedPreferences.getBoolean("isCelsiusUnit", false);
        return new WeatherInfoData(location, weatherDescription, weatherIcon, timeOfDataCalculation, temperature, pressure, windSpeed, windDirection, humidity, isCelsiusUnit);
    }

    public void saveFavoriteLocationsArrayToSharedPreferences() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("WeatherDataFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String favoriteLocationsJson = gson.toJson(WeatherInfoManager.getFavoriteLocationsArray());
        editor.putString("favoriteLocations", favoriteLocationsJson);

        editor.commit();
    }

    public ArrayList<Location> loadFavoriteLocationsArrayFromSharedPreferences() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("WeatherDataFile", Context.MODE_PRIVATE);

        Gson gson = new Gson();

        String favoriteLocationsJson = sharedPreferences.getString("favoriteLocations", null);

        Type type = new TypeToken<ArrayList<Location>>() {}.getType();
        ArrayList<Location> favoriteLocationsArray = gson.fromJson(favoriteLocationsJson, type);

        if (favoriteLocationsArray == null) {
            favoriteLocationsArray = new ArrayList<Location>();
        }

        return favoriteLocationsArray;
    }
}
