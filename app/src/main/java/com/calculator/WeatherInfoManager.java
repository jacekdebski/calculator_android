package com.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;

public class WeatherInfoManager {
    private static WeatherInfoAPIController weatherInfoAPIController;
    private WeatherInfoLoadListener weatherInfoLoadListener;
    private static WeatherInfoData weatherInfoData;
    private Context context;

    WeatherInfoManager(Context context) {
        this.context = context;
        weatherInfoAPIController = new WeatherInfoAPIController(this.context);

        weatherInfoAPIController.setWeatherInfoFetchDataListener(new WeatherInfoFetchDataListener() {

            @Override
            public void onFetchWeatherInfo(WeatherInfoData weatherInfoData) {
                setWeatherInfoData(weatherInfoData);
                saveWeatherInfoDataToSharedPreferences();
                loadWeatherInfoDataFromSharedPreferences();
                weatherInfoLoadListener.onLoadWeatherInfo();
            }
        });
    }

    public WeatherInfoData getWeatherInfoData() {
        return this.weatherInfoData;
    }

    public void setWeatherInfoLoadListener(WeatherInfoLoadListener listener) {
        this.weatherInfoLoadListener = listener;
    }

    public void getWeatherInfo() {
        if (checkInternetConnection()) {
            this.weatherInfoAPIController.fetchWeatherInfo();
//        this.weatherInfoAPIController.fetchGeographicalCoordinates();
        } else {
            Log.i("WeatherInfoManager", "no internet connection");
        }
    }

    private void setWeatherInfoData(WeatherInfoData weatherInfoData) {
        this.weatherInfoData = weatherInfoData;
    }

    private boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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

    private void saveWeatherInfoDataToSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("WeatherDataFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("abc", "abc_value");
        editor.apply(); //it is asynchronous

    }

    private void loadWeatherInfoDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("WeatherDataFile", Context.MODE_PRIVATE);
        String res = sharedPreferences.getString("abc", "default");
        Log.i("Main Activity", res);
    }
}
