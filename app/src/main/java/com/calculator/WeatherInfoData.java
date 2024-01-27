package com.calculator;

public class WeatherInfoData {
    Location location;
    String weatherMain;
    String weatherDescription;
    String weatherIcon;

    WeatherInfoData(Location location, String weatherMain, String weatherDescription, String weatherIcon) {
        this.location = location;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
    }
}
