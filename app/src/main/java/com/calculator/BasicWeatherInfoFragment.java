package com.calculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BasicWeatherInfoFragment extends Fragment implements WeatherInfoFragment {

    private static final String WEATHER_MAIN = "";
    private static final String WEATHER_DESCRIPTION = "";
    private static final String WEATHER_ICON = "";

    private String mWeatherMain;
    private String mWeatherDescription;
    private String mWeatherIcon;

    public BasicWeatherInfoFragment() {
    }

    public static BasicWeatherInfoFragment newInstance(WeatherInfoData weatherInfoData) {
        BasicWeatherInfoFragment fragment = new BasicWeatherInfoFragment();
        Bundle args = new Bundle();
        args.putString(WEATHER_MAIN, weatherInfoData.weatherMain);
        args.putString(WEATHER_DESCRIPTION, weatherInfoData.weatherDescription);
        args.putString(WEATHER_ICON, weatherInfoData.weatherIcon);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWeatherMain = getArguments().getString(WEATHER_MAIN);
            mWeatherDescription = getArguments().getString(WEATHER_DESCRIPTION);
            mWeatherIcon = getArguments().getString(WEATHER_ICON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic_weather_info, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setWeatherInfoData(WeatherInfoManager.getWeatherInfoData());
    }

    @Override
    public void setWeatherInfoData(WeatherInfoData weatherInfoData) {
        Log.i("BasicWeatherInfoFragment", "setWeatherInfoData");

        View view = getView();

        TextView locationNameTextView = view.findViewById(R.id.locationNameTextView);
        locationNameTextView.setText(weatherInfoData.location.locationName);

        TextView latitudeTextView = view.findViewById(R.id.latitudeTextView);
        latitudeTextView.setText(String.valueOf(weatherInfoData.location.geographicalCoordinates.latitude));

        TextView longitudeTextView = view.findViewById(R.id.longitudeTextView);
        longitudeTextView.setText(String.valueOf(weatherInfoData.location.geographicalCoordinates.longitude));

        TextView timeOfDataCalculation = view.findViewById(R.id.timeOfDataCalculationTextView);
        timeOfDataCalculation.setText(String.valueOf(weatherInfoData.timeOfDataCalculation));

        TextView weatherMainTextView = view.findViewById(R.id.weatherDescriptionTextView);
        weatherMainTextView.setText(weatherInfoData.weatherMain);

        TextView temperatureTextView = view.findViewById(R.id.temperatureTextView);
        temperatureTextView.setText(String.valueOf((weatherInfoData.temperature)));

        TextView pressureTextView = view.findViewById(R.id.pressureTextView);
        pressureTextView.setText(String.valueOf(weatherInfoData.pressure));
    }
}