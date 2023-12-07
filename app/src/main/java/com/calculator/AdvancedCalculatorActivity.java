package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdvancedCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);

        Button basicCalculatorSwitcherButton = findViewById(R.id.basicCalculatorSwitcherButton);
        basicCalculatorSwitcherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToBasicCalculator();
            }
        });

    }

    private void switchToBasicCalculator() {
        finish();
        Intent intent = new Intent(this, BasicCalculatorActivity.class);
        startActivity(intent);
    }
}