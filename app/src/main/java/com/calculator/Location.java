package com.calculator;

public class Location {
    GeographicalCoordinates geographicalCoordinates;
    String locationName;

    Location(GeographicalCoordinates geographicalCoordinates, String locationName) {
        this.geographicalCoordinates = geographicalCoordinates;
        this.locationName = locationName;
    }
}
