package com.calculator;

import java.time.ZonedDateTime;

public class WeatherInfoData {
    Location location;
    String weatherDescription;
    String weatherIcon;
    ZonedDateTime timeOfDataCalculation;
    float temperature;
    int pressure;

    WeatherInfoData(Location location, String weatherDescription, String weatherIcon, ZonedDateTime timeOfDataCalculation, float temperature, int pressure) {
        this.location = location;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.timeOfDataCalculation = timeOfDataCalculation;
        this.temperature = temperature;
        this.pressure = pressure;
    }
}
