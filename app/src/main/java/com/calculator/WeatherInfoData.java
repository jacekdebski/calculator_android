package com.calculator;

import java.time.ZonedDateTime;

public class WeatherInfoData {
    Location location;
    String weatherMain;
    String weatherDescription;
    String weatherIcon;
    ZonedDateTime timeOfDataCalculation;

    WeatherInfoData(Location location, String weatherMain, String weatherDescription, String weatherIcon, ZonedDateTime timeOfDataCalculation) {
        this.location = location;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.timeOfDataCalculation = timeOfDataCalculation;
    }
}
