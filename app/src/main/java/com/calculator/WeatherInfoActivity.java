package com.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WeatherInfoActivity extends AppCompatActivity {

    private static WeatherInfoManager weatherInfoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weatherInfoManager = new WeatherInfoManager(getApplicationContext());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.weather_fragment, BasicWeatherInfoFragment.class, null)
                    .commit();
        }
        setContentView(R.layout.activity_weather_info);

        Button basicWeatherInfoButton = findViewById(R.id.basicWeatherInfoButton);
        basicWeatherInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment basicWeatherInfoFragment = new BasicWeatherInfoFragment();
                setFragment(basicWeatherInfoFragment);

            }
        });

        Button additionalWeatherInfoButton = findViewById(R.id.additionalWeatherInfoButton);
        additionalWeatherInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdditionalWeatherInfoFragment additionalWeatherInfoFragment = new AdditionalWeatherInfoFragment();
                setFragment(additionalWeatherInfoFragment);
            }
        });

        Button refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherInfoManager.getWeatherInfo();
            }
        });

        weatherInfoManager.setWeatherInfoLoadListener(new WeatherInfoLoadListener() {
            @Override
            public void onLoadWeatherInfo() {
                Log.i("Main Activity", "onLoadWeatherInfo");
                setWeatherDataToActiveFragment();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.weather_fragment, fragment);
        fragmentTransaction.commit();
    }

    private void setWeatherDataToActiveFragment() {
        WeatherInfoData weatherInfoData = weatherInfoManager.getWeatherInfoData();

        FragmentManager fragmentManager = getSupportFragmentManager();
        WeatherInfoFragment activeFragment = (WeatherInfoFragment) fragmentManager.findFragmentById(R.id.weather_fragment);

        if (activeFragment != null) {
            activeFragment.setWeatherInfoData(weatherInfoData);
        }
    }
}

