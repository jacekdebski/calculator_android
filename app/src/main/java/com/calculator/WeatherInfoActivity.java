package com.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WeatherInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WeatherInfoManager.init(getApplicationContext());

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

        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsFragment settingsFragment = new SettingsFragment();
                setFragment(settingsFragment);
                WeatherInfoManager.tryToFetchWeatherInfoData();
            }
        });

        WeatherInfoManager.setWeatherInfoLoadListener(new WeatherInfoLoadListener() {
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
        WeatherInfoData weatherInfoData = WeatherInfoManager.getWeatherInfoData();

        FragmentManager fragmentManager = getSupportFragmentManager();
        WeatherInfoFragment activeFragment = (WeatherInfoFragment) fragmentManager.findFragmentById(R.id.weather_fragment);

        if (activeFragment != null) {
            activeFragment.setWeatherInfoData(weatherInfoData);
        }
    }
}

