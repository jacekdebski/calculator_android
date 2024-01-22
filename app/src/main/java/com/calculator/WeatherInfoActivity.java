package com.calculator;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WeatherInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Fragment additionalWeatherInfoFragment = new AdditionalWeatherInfoFragment();
                setFragment(additionalWeatherInfoFragment);
            }
        });

    }

    void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.weather_fragment, fragment);
        fragmentTransaction.commit();
    }
}

