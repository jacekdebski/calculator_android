package com.calculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdditionalWeatherInfoFragment extends Fragment implements WeatherInfoFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AdditionalWeatherInfoFragment() {
    }

    public static AdditionalWeatherInfoFragment newInstance(String param1, String param2) {
        AdditionalWeatherInfoFragment fragment = new AdditionalWeatherInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_additional_weather_info, container, false);
    }

    public void onResume() {
        super.onResume();
        setWeatherInfoData(WeatherInfoManager.getWeatherInfoData());
    }

    @Override
    public void setWeatherInfoData(WeatherInfoData weatherInfoData) {
        Log.i("AdditionalWeatherInfoFragment", "setWeatherInfoData");

        View view = getView();

        TextView locationNameTextView = view.findViewById(R.id.locationNameTextView);
        locationNameTextView.setText(weatherInfoData.location.locationName);

        TextView windSpeedTextView = view.findViewById(R.id.windSpeedTextView);
        windSpeedTextView.setText(String.valueOf(weatherInfoData.windSpeed));

        TextView windDirectionTextView = view.findViewById(R.id.windDirectionTextView);
        windDirectionTextView.setText(String.valueOf(weatherInfoData.windDirection));

        TextView humidityTextView = view.findViewById(R.id.humidityTextView);
        humidityTextView.setText(String.valueOf(weatherInfoData.humidity));
    }
}