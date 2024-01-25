package com.calculator;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherInfoAPIController {
    private static Context context;
    private String appid = "977cfc1875e2eee739da6acd35ad0f45";
    private WeatherInfoFetchDataListener weatherInfoFetchDataListener;

    WeatherInfoAPIController(Context context) {
        this.context = context;
    }

    void setWeatherInfoFetchDataListener(WeatherInfoFetchDataListener listener) {
        this.weatherInfoFetchDataListener = listener;
    }

    void fetchWeatherInfo() {
        RequestQueue queue = Volley.newRequestQueue(this.context.getApplicationContext());
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=977cfc1875e2eee739da6acd35ad0f45";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Main Activity", "Response is:" + response);
//                        textView.setText("Response: " + response.toString());

                        try {

                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject weatherObject = weatherArray.getJSONObject(0);
                            int weatherId = weatherObject.getInt("id");
                            String weatherMain = weatherObject.getString("main");
                            String weatherDescription = weatherObject.getString("description");
                            String weatherIcon = weatherObject.getString("icon");

                            if (weatherInfoFetchDataListener != null) {
                                Log.i("Main Activity", "weatherInfoFetchDataListener in onResponse");
                                weatherInfoFetchDataListener.onFetchWeatherInfo(new WeatherInfoData(weatherMain, weatherDescription, weatherIcon));
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
//
//                            long dt = response.getLong("dt");
//
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
//                            String cityName = response.getString("name");
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

    void fetchGeographicalCoordinates() {
        RequestQueue queue = Volley.newRequestQueue(this.context.getApplicationContext());
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid=977cfc1875e2eee739da6acd35ad0f45";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Main Activity", "Response is:" + response);
//                        textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Main Activity", "That didn't work!" + error);
                    }
                });

        queue.add(jsonObjectRequest);
    }

}
