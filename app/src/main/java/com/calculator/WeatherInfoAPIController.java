package com.calculator;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
                            String weatherDescription = weatherObject.getString("description");
                            String weatherIcon = weatherObject.getString("icon");
                            String locationName = response.getString("name");

                            long dt = response.getLong("dt");
                            ZonedDateTime timeOfDataCalculation = Instant.ofEpochSecond(dt).atZone(ZoneId.of("UTC"));
                            Log.i("WeatherInfoApiController", "timeOfDataCalculation is:" + timeOfDataCalculation);

                            JSONObject mainObject = response.getJSONObject("main");
                            float temperature = (float) mainObject.getDouble("temp");
                            int pressure = mainObject.getInt("pressure");
                            int humidity = mainObject.getInt("humidity");

                            JSONObject windObject = response.getJSONObject("wind");
                            float windSpeed = (float) windObject.getDouble("speed");
                            float windDirection = (float) windObject.getDouble("deg");

                            if (weatherInfoFetchDataListener != null) {
                                Location location = new Location(geographicalCoordinates, locationName);
                                weatherInfoFetchDataListener.onFetchWeatherInfo(new WeatherInfoData(location, weatherDescription, weatherIcon, timeOfDataCalculation, temperature, pressure, windSpeed, windDirection, humidity));
                            } else {
                                Log.i("WeatherInfoAPIController", "weatherInfoFetchDataListener is null");
                            }
                        } catch (JSONException e) {
                            Toast.makeText(context, "weather data info is not found for this city", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("WeatherInfoAPIController", "That didn't work!" + error);
                        Toast.makeText(context, "failed to fetch weather info from the API", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(context, "weather data info is not found for this city", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("WeatherAPIController", "That didn't work!" + error);
                        Toast.makeText(context, "failed to fetch weather info from the API", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(jsonObjectRequest);
    }

}
