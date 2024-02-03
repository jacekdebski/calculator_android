package com.calculator;

import java.time.ZonedDateTime;

public class WeatherInfoData {
    Location location;
    String weatherDescription;
    String weatherIcon;
    ZonedDateTime timeOfDataCalculation;
    float temperature;
    int pressure;
    float windSpeed;
    float windDirection;
    int humidity;
    boolean isCelsiusUnit;

    WeatherInfoData(Location location, String weatherDescription, String weatherIcon, ZonedDateTime timeOfDataCalculation, float temperature, int pressure, float windSpeed, float windDirection, int humidity, boolean isCelsiusUnit) {
        this.location = location;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.timeOfDataCalculation = timeOfDataCalculation;
        this.temperature = temperature;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.isCelsiusUnit = isCelsiusUnit;
    }
}
