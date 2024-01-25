package com.calculator;

import android.content.Context;

public class WeatherInfoManager {
    private static WeatherInfoAPIController weatherInfoAPIController;
    private WeatherInfoLoadListener weatherInfoLoadListener;
    private static WeatherInfoData weatherInfoData;

    WeatherInfoManager(Context context) {
        weatherInfoAPIController = new WeatherInfoAPIController(context);

        weatherInfoAPIController.setWeatherInfoFetchDataListener(new WeatherInfoFetchDataListener() {

            @Override
            public void onFetchWeatherInfo(WeatherInfoData weatherInfoData) {
                setWeatherInfoData(weatherInfoData);
                weatherInfoLoadListener.onLoadWeatherInfo();
            }
        });
    }

    void setWeatherInfoData (WeatherInfoData weatherInfoData) {
        this.weatherInfoData = weatherInfoData;
    }

    WeatherInfoData getWeatherInfoData() {
        return this.weatherInfoData;
    }

    void setWeatherInfoLoadListener(WeatherInfoLoadListener listener) {
        this.weatherInfoLoadListener = listener;
    }

    void getWeatherInfo() {
        this.weatherInfoAPIController.fetchWeatherInfo();
//        this.weatherInfoAPIController.fetchGeographicalCoordinates();
    }
}
