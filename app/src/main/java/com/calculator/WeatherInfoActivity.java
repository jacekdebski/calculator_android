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

        setContentView(R.layout.activity_weather_info);

        if (savedInstanceState == null) {
            if (getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.weather_basic_fragment, new BasicWeatherInfoFragment());
                fragmentTransaction.add(R.id.weather_additional_fragment, new AdditionalWeatherInfoFragment());
                fragmentTransaction.commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.weather_fragment, BasicWeatherInfoFragment.class, null)
                        .commit();
            }
        }

        if (getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {

            Button weatherInfoButton = findViewById(R.id.weatherInfoButton);
            weatherInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BasicWeatherInfoFragment basicWeatherInfoFragment = new BasicWeatherInfoFragment();
                    setFragment(basicWeatherInfoFragment, R.id.weather_basic_fragment);

                    AdditionalWeatherInfoFragment additionalWeatherInfoFragment = new AdditionalWeatherInfoFragment();
                    setFragment(additionalWeatherInfoFragment, R.id.weather_additional_fragment);
                }
            });

            Button settingsButton = findViewById(R.id.settingsLandscapeButton);
            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SettingsFragment settingsFragment = new SettingsFragment();
                    setFragment(settingsFragment, R.id.weather_additional_fragment);
                }
            });

        } else {
            Button basicWeatherInfoButton = findViewById(R.id.basicWeatherInfoButton);
            basicWeatherInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment basicWeatherInfoFragment = new BasicWeatherInfoFragment();
                    setFragment(basicWeatherInfoFragment, R.id.weather_fragment);

                }
            });

            Button additionalWeatherInfoButton = findViewById(R.id.additionalWeatherInfoButton);
            additionalWeatherInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AdditionalWeatherInfoFragment additionalWeatherInfoFragment = new AdditionalWeatherInfoFragment();
                    setFragment(additionalWeatherInfoFragment, R.id.weather_fragment);
                }
            });

            Button settingsButton = findViewById(R.id.settingsButton);
            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SettingsFragment settingsFragment = new SettingsFragment();
                    setFragment(settingsFragment, R.id.weather_fragment);
                }
            });
        }


        WeatherInfoManager.setWeatherInfoLoadListener(new WeatherInfoLoadListener() {
            @Override
            public void onLoadWeatherInfo() {
                Log.i("Main Activity", "onLoadWeatherInfo");
                setWeatherDataToActiveFragment();
            }
        });
    }

    private void setFragment(Fragment fragment, int containerViewId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    private void setWeatherDataToActiveFragment() {
        WeatherInfoData weatherInfoData = WeatherInfoManager.getWeatherInfoData();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {

            Fragment weatherBasicFragment = fragmentManager.findFragmentById(R.id.weather_basic_fragment);
            ((WeatherInfoFragment) weatherBasicFragment).setWeatherInfoData(weatherInfoData);

        } else {

            Fragment activeFragment = fragmentManager.findFragmentById(R.id.weather_fragment);

            if (activeFragment != null) {
                if (activeFragment instanceof WeatherInfoFragment) {
                    ((WeatherInfoFragment) activeFragment).setWeatherInfoData(weatherInfoData);
                }
            }
        }
    }
}

