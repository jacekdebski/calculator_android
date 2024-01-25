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

    private void setWeatherInfoData(WeatherInfoData weatherInfoData) {
        this.weatherInfoData = weatherInfoData;
    }

    public WeatherInfoData getWeatherInfoData() {
        return this.weatherInfoData;
    }

    public void setWeatherInfoLoadListener(WeatherInfoLoadListener listener) {
        this.weatherInfoLoadListener = listener;
    }

    public void getWeatherInfo() {
        this.weatherInfoAPIController.fetchWeatherInfo();
//        this.weatherInfoAPIController.fetchGeographicalCoordinates();
    }
}
