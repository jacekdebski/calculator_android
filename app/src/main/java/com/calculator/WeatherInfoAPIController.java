package com.calculator;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class WeatherInfoAPIController {
    private static Context context;
    private String baseURL = "https://api.openweathermap.org";
    private String appid = "977cfc1875e2eee739da6acd35ad0f45";
    private WeatherInfoFetchDataListener weatherInfoFetchDataListener;

    WeatherInfoAPIController(Context context) {
        this.context = context;
    }

    void setWeatherInfoFetchDataListener(WeatherInfoFetchDataListener listener) {
        this.weatherInfoFetchDataListener = listener;
    }

    void fetchWeatherInfo(GeographicalCoordinates geographicalCoordinates) {
        RequestQueue queue = Volley.newRequestQueue(this.context.getApplicationContext());
        String url = baseURL + "/data/2.5/weather?lat=" + geographicalCoordinates.latitude + "&lon=" + geographicalCoordinates.longitude + "&appid=" + appid;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("WeatherInfoApiController", "Response is:" + response);
                        try {

                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject weatherObject = weatherArray.getJSONObject(0);
                            int weatherId = weatherObject.getInt("id");
                            String weatherMain = weatherObject.getString("main");
                            String weatherDescription = weatherObject.getString("description");
                            String weatherIcon = weatherObject.getString("icon");
                            String locationName = response.getString("name");

                            long dt = response.getLong("dt");
                            ZonedDateTime timeOfDataCalculation = Instant.ofEpochSecond(dt).atZone(ZoneId.of("UTC"));
                            Log.i("WeatherInfoApiController", "timeOfDataCalculation is:" + timeOfDataCalculation);

                            if (weatherInfoFetchDataListener != null) {
                                Location location = new Location(geographicalCoordinates, locationName);
                                weatherInfoFetchDataListener.onFetchWeatherInfo(new WeatherInfoData(location, weatherMain, weatherDescription, weatherIcon, timeOfDataCalculation));
                            } else {
                                Log.i("Main Activity", "weatherInfoFetchDataListener is null");
                            }

//                            String base = response.getString("base");
//
//                            JSONObject main = response.getJSONObject("main");
//                            double temp = main.getDouble("temp");
//                            double feelsLike = main.getDouble("feels_like");
//                            double tempMin = main.getDouble("temp_min");
//                            double tempMax = main.getDouble("temp_max");
//                            int pressure = main.getInt("pressure");
//                            int humidity = main.getInt("humidity");
//                            int seaLevel = main.getInt("sea_level");
//                            int groundLevel = main.getInt("grnd_level");
//
//                            int visibility = response.getInt("visibility");
//
//                            JSONObject wind = response.getJSONObject("wind");
//                            double windSpeed = wind.getDouble("speed");
//                            double windDeg = wind.getDouble("deg");
//                            double windGust = wind.getDouble("gust");
//
//                            JSONObject clouds = response.getJSONObject("clouds");
//                            int cloudiness = clouds.getInt("all");
////
//                            JSONObject sys = response.getJSONObject("sys");
//                            int sysType = sys.getInt("type");
//                            int sysId = sys.getInt("id");
//                            String sysCountry = sys.getString("country");
//                            long sunrise = sys.getLong("sunrise");
//                            long sunset = sys.getLong("sunset");
//
//                            int timezone = response.getInt("timezone");
//
//                            int cityId = response.getInt("id");
//                            int cod = response.getInt("cod");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Main Activity", "That didn't work!" + error);
                    }
                });

        queue.add(jsonObjectRequest);
    }

    void fetchGeographicalCoordinates(String location) {
        RequestQueue queue = Volley.newRequestQueue(this.context.getApplicationContext());
        String url = baseURL + "/geo/1.0/direct?q=" + location + "&limit=5&appid=" + appid;

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(0);
                            float latitude = (float) jsonObject.optDouble("lat");
                            float longitude = (float) jsonObject.optDouble("lon");
                            Log.i("WeatherAPIController", "latitude: " + latitude + " longitude:" + longitude);
                            GeographicalCoordinates geographicalCoordinates = new GeographicalCoordinates(latitude, longitude);
                            fetchWeatherInfo(geographicalCoordinates);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("WeatherAPIController", "That didn't work!" + error);
                    }
                });

        queue.add(jsonObjectRequest);
    }

}
